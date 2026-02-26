public class Piece {
    /*
    Pieces have three attributes

        name(String): a single letter abbreviation of the name of the piece
        player(String): a string ("white" or "black") that dictates which player the piece belongs to
        hasMoved(boolean): this indicates whether or not a piece has moved. By default it is false
                            when a piece is moved, it should be set to be true
     */
    private String name;
    private String player;
    private boolean hasMoved;

    //NOTE: takes 2 parameters not 3
    public Piece(String name, String player){
        this.name = name;
        this.player = player;
        this.hasMoved = false;
    }

    //gets the name attribute
    public String getName(){
        return name;
    }
    
    //gets the player attribute
    public String getPlayer(){
        return player;
    }

    //gets the hasMoved attribute
    public boolean getHasMoved(){
        return hasMoved;
    }

    //sets the hasMoved attribute
    public void setHasMoved(boolean moved){
        hasMoved = moved;
    }

    /*
    This toString has a condition
        if the player attribute is white, an uppercase letter is returned
        if the player attribute is black, a lowercase letter is returned
     */
    public String toString(){
        if (player == "white"){
            return name.toUpperCase();
        } else {
            return name;
        }
        
    }
}
