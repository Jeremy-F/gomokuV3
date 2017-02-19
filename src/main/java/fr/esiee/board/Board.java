package fr.esiee.board;
import fr.esiee.GameIHM;
import fr.esiee.core.Alignment;
import fr.esiee.core.Movement;
import fr.esiee.player.Player;
import javafx.beans.property.SimpleObjectProperty;
import java.util.ArrayList;
import java.util.Arrays;
/**
 *****************************************************
 * ,----.     E3T - Esiee Paris      ,--.            *
 * '  .-./    ,---. ,--,--,--. ,---. |  |,-.,--.,--. *
 * |  | .---.| .-. ||        || .-. ||     /|  ||  | *
 * '  '--'  |' '-' '|  |  |  |' '-' '|  \  \'  ''  ' *
 * `------'  `---' `--`--`--' `---' `--'`--'`------' *
 *    Alexandre Causse            Jérémy Fornarino   *
 *****************************************************
 * @author Alexandre Causse & Jérémy Fornarino   [E3T]
 */
public class Board {

    public final static int MAXPOINT = 1000;
    public final static int MINPOINT = -1000;
    private final GameIHM game;


    private Player[] players = new Player[2];
    private ArrayList<SimpleObjectProperty<Box>> boxes;
    private int nbAlignedBoxesForWin;

    private ArrayList<Movement> movementsStory;


    public Board(int size, int nbAlignedBoxesForWin) {
        this(size, nbAlignedBoxesForWin, null,null);
    }
    public Board(int size, int nbAlignedBoxesForWin, Player player1, Player player2) {
        this(null, size, nbAlignedBoxesForWin, player1, player2);
    }
    public Board(GameIHM game, int size, int nbAlignedBoxesForWin, Player player1, Player player2) {
        this.game = game;
        this.nbAlignedBoxesForWin = nbAlignedBoxesForWin;
        this.boxes = new ArrayList<SimpleObjectProperty<Box>>();
        for(int line = 0; line < size; line++){
            for(int column = 0; column < size; column++){
                boxes.add(new SimpleObjectProperty<Box>(new Box(line, column)));
            }
        }
        this.setPlayer(0, player1);
        this.setPlayer(1, player2);

        this.movementsStory = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Board{" +
                "players=" + Arrays.toString(players) +
                ", boxes=" + toStringBoxes() +
                ", nbAlignedBoxesForWin=" + nbAlignedBoxesForWin +
                ", movementsStory=" + movementsStory +
                '}';
    }

    public boolean canBeWin(){
        ArrayList<Alignment> alignments = this.getAllAlignment();
        for(Alignment alignment : alignments){
            if(alignment.canBeWin(this.remainingMoves())){
                return true;
            }
        }
        return false;
    }

    private int remainingMoves() {
        return (this.getBoxes().size()) - this.getNumberOfMove();
    }

    private String toStringBoxes() {
        String retour = "";
        for (SimpleObjectProperty<Box> box : this.boxes) {
            retour += box.get().toString() + "\n";
        }
        return retour;
    }
    /**
     * Have the dimension of the board (X*X --> Return X)
     * @return
     */
    public int dimension(){
        return (int) Math.sqrt(this.getBoxes().size());
    }

    /*************************************************************************
     *                   You can have all different ALIGNEMENT               *
     *************************************************************************/
    /**
     * // TODO La javadoc de generic scan
     * @param line
     * @param column
     * @param dLine
     * @param dColumn
     * @return
     */
    public Alignment genericScan(int line, int column, int dLine, int dColumn){
        Alignment alignment = new Alignment(this.nbAlignedBoxesForWin);
        int size = this.dimension();
        for (int currentLine = line, currentColumn = column;
             currentLine >= 0 && currentLine < size && currentColumn < size && currentColumn >= 0;
             currentLine += dLine, currentColumn +=dColumn){
            alignment.add(this.getBox(currentLine, currentColumn));
        }
        return alignment;
    }
    /**
     * Returns the line according to the given number as a parameter
     * @param lineNumber The number of the line needed
     * @return An ArrayList with all the {@link Box} on the line
     */
    public Alignment getBoxLine(int lineNumber){
        return genericScan(lineNumber, 0, 0, 1);
    }
    /**
     * Return the column according to the given number as a parameter
     * @param columnNumber The number of the column needed
     * @return An ArrayList with all the {@link Box} on the column
     */
    public Alignment getBoxColumn(int columnNumber){
        return genericScan(0, columnNumber, 1, 0);
    }
    /**
     * Return the diagonal North-West to South-Est according to the given starting {@link Box}
     * @param line The line number of the first box
     * @param column The line column of the first box
     * @return The Diagonal {@link ArrayList<Box>}
     */
    public Alignment getDiagonalNwToSe(int line, int column){
        return genericScan(line,column, 1, 1);
    }
    /**
     * Return the diagonal South-West to North-Est according to the given starting {@link Box}
     * @param line The line number of the first box
     * @param column The line column of the first box
     * @return The Diagonal {@link ArrayList<Box>}
     */
    public Alignment getDiagonalSwToNe(int line, int column){
        return genericScan(line,column, -1, 1);
    }
    /**
     * Give all the possible alignement
     * @return A list containing all the alignment
     */
    public ArrayList<Alignment> getAllAlignment(){
        // Creating the Output Table
        ArrayList<Alignment> allAlignment = new ArrayList<>();
        int size = dimension();

        //Add line and columns
        for(int i = 0; i < size; i++) {
            // Add Column
            allAlignment.add(this.getBoxColumn(i));
            // Add Line
            allAlignment.add(this.getBoxLine(i));
        }
        //Add all diagonal
        for(int line = 0 ; line < size; line++){
            for(int column = 0; column < size; column++){
                //We need only the top, left and bottom lines.
                if ( (line == 0 || column == 0) || line == size - 1) {
                    allAlignment.add(this.getDiagonalNwToSe(line,column));
                    allAlignment.add(this.getDiagonalSwToNe(line,column));
                }
            }
        }
        // We remove all the Alignment below the minimum size
        allAlignment.removeIf(alignment -> alignment.size() < this.nbAlignedBoxesForWin);
        // We return the output table
        return allAlignment;
    }

    /**
     * Select one box
     * @param line of the box (i)
     * @param column of the box (j)
     * @return The {@link Board} object corresponding
     */
    public Box getBox(int line, int column){
        SimpleObjectProperty<Box> boxProperty = this.getBoxProperty(line, column);
        return (boxProperty != null) ? boxProperty.get() : null;
    }
    /**
     * Select one box
     * @param line of the box (i)
     * @param column of the box (j)
     * @return A SimpleObjectProperty with the {@link Box} inside
     */
    public SimpleObjectProperty<Box> getBoxProperty(int line, int column){
        return this.boxes.get(getIndexBox(line,column));
    }
    public int getIndexBox(int line, int column){
        for(int i = 0; i < this.boxes.size(); i++){
            Box box = (Box) this.boxes.get(i).get();
            if(box.getLine() == line &&  box.getColumn() == column){
                return i;
            }
        }
        return -1;
    }
    public ArrayList<Box> getBoxes() {
        ArrayList<Box> boxes = new ArrayList<>();
        this.boxes.forEach(boxSimpleObjectProperty -> {
            boxes.add(boxSimpleObjectProperty.get());
        });
        return boxes;
    }
    public ArrayList<Box> getAllEmptyBox() {
        ArrayList<Box> emptyBox = new ArrayList<>();
        for(Box box : this.getBoxes()){
            if(!box.hasOwner()){
                emptyBox.add(box);
            }
        }
        return emptyBox;
    }

    public Player whoWon(){
        for(Alignment alignment : this.getAllAlignment()){
            Player winner = alignment.earnedBy();
            if(winner != null){
                //System.out.println("Winner is : " + winner + " on " + alignment);
                return winner;
            }
        }

        return null;
    }
    /**
     *
     * @return
     */
    public boolean isWon(){
        return  whoWon() != null;
    }
    /**
     * Determines if the game is over
     * @return true if the game is over, else false
     */
    public boolean isFinished(){
        return this.isWon() || this.getAllEmptyBox().size() == 0;
    }

    /*************************************************************************
     *                                                                       *
     *                              Players                                  *
     *                                                                       *
     *************************************************************************/
    public Player[] getPlayers() {
        return players;
    }
    public Player getPlayer(int i) {
        return this.getPlayers()[i];
    }
    public void setPlayer(int index, Player player) {
        this.players[index] = player;
    }
    /*************************************************************************
     *                                                                       *
     *                              Current player                           *
     *                                                                       *
     *************************************************************************/
     public int getCurrentIndexPlayer(){
         // By default the first player to play is at the index 0
         if(this.getNumberOfMove() <= 0){
             return 0;
         }
         return (this.getNextIndexPlayer() == 0)?1:0;

    }
    public Player getCurrentPlayer(){
        return this.getPlayer(this.getCurrentIndexPlayer());
    }

    /*************************************************************************
     *                                                                       *
     *                               Next player                             *
     *                                                                       *
     *************************************************************************/
    public int getNextIndexPlayer(){
        Movement lastMovement = this.getLastMove();
        return lastMovement.getPlayerIndex();
    }
    public Player getNextPlayer(){
        return this.getPlayer(this.getNextIndexPlayer());
    }


    /*************************************************************************
     *                                                                       *
     *                               Last move                               *
     *                                                                       *
     *************************************************************************/
    public int getLastIndexBoxPlayed() {
        ArrayList<Movement> movementsStory = this.getMovementsStory();
        Movement mvnt = movementsStory.get(movementsStory.size() - 1);
        return getIndexBox(mvnt.getLine(), mvnt.getColumn());
    }
    public Box getLastBoxPlayed(){
        return this.boxes.get(this.getLastIndexBoxPlayed()).get();
    }
    public Movement getLastMove(){
        int numberOfMove = this.getNumberOfMove();
        if (numberOfMove <= 0) {
            return null;
        }
        return this.getMovementsStory().get(numberOfMove - 1);
    }


    /*************************************************************************
     *                                                                       *
     *                               Play the Game                           *
     *                                                                       *
     *************************************************************************/
    public void play(int line, int column) {
        play(line,column,-1);
    }
    public void play(int line, int column, long executionTime){
        if(!this.isFinished()) {
            Box box = this.getBox(line,column);
            if(!box.hasOwner()){
                box.setOwner(this.getCurrentPlayer());
                this.addMovement(line, column, executionTime);
            }
        }else{
            System.out.println(this);
        }
    }
    /**
     * /!\ This Method can be use only one time after playing
     */
    public void cancelLastMove(){
        if(this.getNumberOfMove() > 0) {
            this.getLastBoxPlayed().setOwner(null);
            this.removeLastMovement();
        }else{
            System.out.println("Le nombre de move est trop faible");
        }
    }
    public void graphLaunch(){
        if(this.game != null){
            if(!this.isFinished()) {
                this.getCurrentPlayer().play(this);
            }
            this.game.updateGridPane();
        }
    }
    public void launch(){
        while(!this.isFinished()){
            long debut = System.currentTimeMillis();
            this.getCurrentPlayer().play(this);

        }
        System.out.println(this);
        System.out.println("And the winner is : "+this.whoWon());
        System.out.println("Score for "+this.getPlayer(0) + " : "+this.scoreFor(this.getPlayer(0)));
        System.out.println("Score for "+this.getPlayer(1) + " : "+this.scoreFor(this.getPlayer(1)));
    }
    /*************************************************************************
     *                                                                       *
     *                               Score                                   *
     *                                                                       *
     *************************************************************************/
    public int scoreFor(Player player){
        if(player.equals(this.whoWon())){
            // In this case, the player past in param' won
            return MAXPOINT - this.getNumberOfMove();
        }else if(isWon()){
            return MINPOINT + this.getNumberOfMove();
        }else{
            return player.evaluate(this);
        }
    }
    /*************************************************************************
     *                                                                       *
     *                               Move history                            *
     *                                                                       *
     *************************************************************************/
    public ArrayList<Movement> getMovementsStory() {
        return movementsStory;
    }
    public int getNumberOfMove(){
        return this.getMovementsStory().size();
    }
    private void addMovement(int line, int column, long executionTime) {
        Movement movement = new Movement(line, column, this.getCurrentIndexPlayer(), executionTime);
        this.getMovementsStory().add(movement);
    }
    private void removeLastMovement() {
        ArrayList<Movement> movementsStory = this.getMovementsStory();
        movementsStory.remove(this.getNumberOfMove() - 1);
    }

    public Player getOtherPlayer(Player secondIAPlayer) {
        return secondIAPlayer.equals(this.getPlayer(0))? this.getPlayer(1) : this.getPlayer(0);
    }

    public int getnumberOfAlignementsOf(Player player, int alignementSize) {
        int total = 0;
        ArrayList<Alignment> allAlignements = this.getAllAlignment();
        for(Alignment alignment : allAlignements){
            if(alignment.size() >= alignementSize){
                total += alignment.countNbrOfBoxAligneBy(player, alignementSize);
            }
        }
        return total;
    }
}
