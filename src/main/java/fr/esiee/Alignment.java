package fr.esiee;


import fr.esiee.Player.Player;

import java.util.ArrayList;
import java.util.Collections;

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
public class Alignment {
    private ArrayList<Box> boxes;
    private int nbAlignedBoxesForWin;

    /**
     * Default Constructor
     * Create a new ArrayList for the {@link Alignment#boxes}
     * Initialize the {@link Alignment#nbAlignedBoxesForWin} at 1 (default value)
     */
    public Alignment() {
        this(new ArrayList<>(), 1);
    }

    /**
     * Constructor
     * Create a new ArrayList for the {@link Alignment#boxes}
     * @param nbAlignedBoxesForWin The number of points needed to make the lineup win  {@link Alignment#nbAlignedBoxesForWin}
     */
    public Alignment(int nbAlignedBoxesForWin) {
        this(new ArrayList<>(), nbAlignedBoxesForWin);
    }

    /**
     * Constrcuteur (Private)
     * @param boxes The arraylist containing the boxes
     * @param nbAlignedBoxesForWin The number of points needed to make the lineup win {@link Alignment#nbAlignedBoxesForWin}
     */
    private Alignment(ArrayList<Box> boxes, int nbAlignedBoxesForWin) {
        this.boxes = boxes;
        this.nbAlignedBoxesForWin = nbAlignedBoxesForWin;
    }
    /**
     * Select one box
     * @param i The position of the box
     * @return The box if she exist, null else
     */
    public Box getBox(int i){
        return this.getBoxes().get(i);
    }

    /**
     * Get the ArrayList with all the boxes
     * @return An arraylist with all the boxes contained in the "Alignment"
     */
    public ArrayList<Box> getBoxes() {
        return this.boxes;
    }

    /**
     * Add a box in the Alignement
     * @param box The box to add in the lignement
     * @return Current object (this)
     */
    public Alignment add(Box box){
        return this.addAll(box);
    }
    public Alignment addAll(Box... boxes){
        Collections.addAll(this.boxes, boxes);
        return this;
    }

    public Alignment remove(Box box){
        this.boxes.remove(box);
        return this;
    }
    public boolean canBeWin(int remainingMoves){
        if(!this.earned()){
            int numberOfEmptyBoxes = this.numberOfEmptyBoxes();
            if(numberOfEmptyBoxes != 0 || this.nbAlignedBoxesForWin > this.size()){
                if( (numberOfEmptyBoxes == this.size() - 2) && (numberOfEmptyBoxes+1 == this.nbAlignedBoxesForWin) ){
                    return true;
                }else if(maxPossibleAlignement(remainingMoves) >= this.nbAlignedBoxesForWin){
                    return true;
                }
            }
            return false;
        }return false;
    }
    public int maxPossibleAlignement(int remainingMoves){
        int maxAlignementPossible = 0;
        for(int i = 0; i < this.size(); i++){
            int numberOfCurrentAlignement = 0;
            Player lastPlayer = null;

            for(int j = i, currentRemainingMoves = (remainingMoves/2); j < this.size() && currentRemainingMoves > 0; j++, currentRemainingMoves--){
                Box box = this.getBox(j);
                Player owner = box.getOwner();
                if(owner != null){
                    if(lastPlayer == null || owner.equals(lastPlayer)){
                        numberOfCurrentAlignement++;
                    }else {
                        numberOfCurrentAlignement = 0;
                    }
                }else {
                    numberOfCurrentAlignement++;
                }
                lastPlayer = owner;
                if(numberOfCurrentAlignement >= maxAlignementPossible){
                    maxAlignementPossible = numberOfCurrentAlignement;
                }
            }
        }
        return maxAlignementPossible;
    }
    public int numberOfEmptyBoxes(){
        final int[] total = {0};
        this.boxes.forEach(box -> {
            if(!box.hasOwner()){
                total[0]++;
            }
        });
        return total[0];
    }
    public boolean earned(){
        return this.earnedBy() != null;
    }
    /**
     *
     * To know if a player "have win" an alignment
     * @param player The player
     * @return TRUE if the player (param) have win, else false
     */
    public boolean earnedBy(Player player){
        return earnedBy() == player;
    }

    /**
     * Get the player who win the alignment
     * @return The player if someone have win, else null
     */
    public Player earnedBy(){
        int currentPlayerBoxNumber = 1;
        Player lastPlayer = this.getBox(0).getOwner();
        for(int i = 1; i < this.size(); i++){
            final Box box = this.getBox(i);
            if(box.hasOwner()) {
                Player owner = box.getOwner();
                if (!owner.equals(lastPlayer)) {
                    currentPlayerBoxNumber = 1;
                }
                else {
                    currentPlayerBoxNumber++;
                    if(currentPlayerBoxNumber >= this.nbAlignedBoxesForWin)
                        return owner;
                }
                lastPlayer = owner;
            }else{
                currentPlayerBoxNumber = 1;
                lastPlayer = null;
            }
        }
        return null;
    }

    /**
     * Return the size of the alignement
     * @return the size of the alignement (int)
     */
    public int size(){
        return this.getBoxes().size();
    }

    /**
     * Compares the specified object with this alignment for equality.
     * @param o The object to compare
     * @return true if the object are the sime, else false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alignment alignment = (Alignment) o;
        for(int i = 0; i < this.getBoxes().size(); i++){
            if(this.getBox(i).equals(alignment.getBox(i))){
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Alignment{" +
                "boxes=" + boxes.toString() +
                ", nbAlignedBoxesForWin=" + nbAlignedBoxesForWin +
                '}';
    }
    public boolean myPlayerHasXAlignedBoxes(Player player, int size) {
        int currentPlayerBoxNumber = 0;
        for(int i = 0; i < this.size(); i++){
            final Box box = this.getBox(i);
            if(box.hasOwner()) {
                Player owner = box.getOwner();
                if (!owner.equals(player)) {
                    currentPlayerBoxNumber = 0;
                }else {
                    currentPlayerBoxNumber++;
                    if(currentPlayerBoxNumber >= size)
                        return true;
                }
            }else{
                currentPlayerBoxNumber = 0;
            }
        }
        return false;
    }

    public int countNbrOfBoxAligneBy(Player player, int nbr){
        if (nbr==0) return 0;
        int total = 0;
        for(int i = 0; i <= this.size() - nbr; i++){
            total++;
            for(int j = 0; j < nbr; j++){
                final Box box = this.getBox(j+i);
                if(!player.equals(box.getOwner())){
                    total--;
                    break;
                }
            }
        }
        return total;
    }
}
