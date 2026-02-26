//Why are you here? You aren't supposed to be here

public class MoveValidator {
    public static boolean isVertMove(int[] start, int[] end, Board board){
        int x1 = start[0];
        int y1 = start[1];
        int x2 = end[0];
        int y2 = end[1];

        if(y1 != y2 && x1 == x2){
            return MoveValidator.spacesFreeVert(start, end, board);
        } else {
            return false;
        }
    }

    public static boolean isHorzMove(int[] start, int[] end, Board board){
        int x1 = start[0];
        int y1 = start[1];
        int x2 = end[0];
        int y2 = end[1];

        if(x1 != x2 && y1 == y2){
            return MoveValidator.spacesFreeHorz(start, end, board);
        } else {
            return false;
        }        
    }

    public static boolean isDiagMove(int[] start, int[] end, Board board){
        int x1 = start[0];
        int y1 = start[1];
        int x2 = end[0];
        int y2 = end[1];

        if(y2-y1 != 0 && x2-x1 != 0){
            if(Math.abs((y2-y1)/(x2-x1)) == 1){
                return MoveValidator.spacesFreeDiag(start, end, board);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    public static boolean spacesFreeVert(int[] start, int[] end, Board board){
        int x = start[0];
        int y1 = start[1];
        int y2 = end[1];

        int yStep;
        if(y2-y1 > 0){
            yStep = 1;
        } else {
            yStep = -1;
        }

        for(int i = 1; i < Math.abs(y2-y1); i++){
            if(board.getBoard().get(y1+(yStep*i)).get(x) != null){
                return false;
            }
        }
        return true;
    }

    public static boolean spacesFreeHorz(int[] start, int[] end, Board board){
        int y = start[1];
        int x1 = start[0];
        int x2 = end[0];


        int xStep;
        if(x2-x1 > 0){
            xStep = 1;
        } else {
            xStep = -1;
        }

        for(int i = 1; i < Math.abs(x2-x1); i++){
            if(board.getBoard().get(y).get(x1+(xStep*i)) != null){
                return false;
            }
        }
        return true;
    }

    public static boolean spacesFreeDiag(int[] start, int[] end, Board board){
        int x1 = start[0];
        int y1 = start[1];
        int x2 = end[0];
        int y2 = end[1];

        int xStep;
        if(x2-x1 > 0){
            xStep = 1;
        } else {
            xStep = -1;
        }

        int yStep;
        if(y2-y1 > 0){
            yStep = 1;
        } else {
            yStep = -1;
        }

        for(int i = 1; i < Math.abs(x2-x1); i++){
            if(board.getBoard().get(y1+(yStep*i)).get(x1+(xStep*i)) != null){
                return false;
            }
        }
        return true;
    }
    

    public static boolean isValidTarget(int[] start, int[] end, Board board){
        int x1 = start[0];
        int y1 = start[1];
        int x2 = end[0];
        int y2 = end[1];

        //target must not be the same as current position
        if(x1 == x2 && y1 == y2){
            return false;
        }

        //target must be inside the board
        if(x2 > 8 || x2 < 0 ||
           y2 > 8 || y2 < 0){
            return false;
        }

        //target must not have friendly piece 
        if(board.getBoard().get(y2).get(x2) != null && board.getBoard().get(y1).get(x1).getPlayer() == board.getBoard().get(y2).get(x2).getPlayer()){
            return false;
        }
    
        return true;
    }

    public static boolean isValidKing(int[] start, int[] end, Board board){
        // System.out.println("checking king...");

        int x1 = start[0];
        int y1 = start[1];
        int x2 = end[0];
        int y2 = end[1];

        if(
            (MoveValidator.isDiagMove(start, end, board) || MoveValidator.isHorzMove(start, end, board) || MoveValidator.isVertMove(start, end, board)) 
            && 
            (Math.abs(x2-x1) <= 1 && Math.abs(y2-y1) <= 1)
            &&
            (MoveValidator.isValidTarget(start, end, board))
        ){
            return true;
        } else {
            return false;
        }
    }

    public static boolean isValidQueen(int[] start, int[] end, Board board){
        // System.out.println("checking queen...");

        if(
            (MoveValidator.isDiagMove(start, end, board) || MoveValidator.isHorzMove(start, end, board) || MoveValidator.isVertMove(start, end, board)) 
            && 
            (MoveValidator.isValidTarget(start, end, board))
        ){
            return true;
        } else {
            return false;
        }
    }

    public static boolean isValidRook(int[] start, int[] end, Board board){
        // System.out.println("checking rook...");

        if(
            (MoveValidator.isHorzMove(start, end, board) || MoveValidator.isVertMove(start, end, board)) 
            && 
            (MoveValidator.isValidTarget(start, end, board))
        ){
            return true;
        } else {
            return false;
        }
    }

    public static boolean isValidBishop(int[] start, int[] end, Board board){
        // System.out.println("checking bishop...");

        if(
            (MoveValidator.isDiagMove(start, end, board)) 
            && 
            (MoveValidator.isValidTarget(start, end, board))
        ){
            return true;
        } else {
            return false;
        }
    }

    public static boolean isValidKnight(int[] start, int[] end, Board board){

        int x1 = start[0];
        int y1 = start[1];
        int x2 = end[0];
        int y2 = end[1];

        if(
            ((Math.abs(x2-x1) == 1 && Math.abs(y2-y1) == 2)
            ||
            (Math.abs(x2-x1) == 2 && Math.abs(y2-y1) == 1))
            &&
            (MoveValidator.isValidTarget(start, end, board))
        ){
            return true;
        } else {
            return false;
        }
        
    }

    public static boolean isValidPawn(int[] start, int[] end, Board board){
        // System.out.println("checking pawn...");

        int x1 = start[0];
        int y1 = start[1];
        int x2 = end[0];
        int y2 = end[1];

        //for 2 square move
        if(
            (MoveValidator.isVertMove(start, end, board)) 
            && 
            (Math.abs(y2-y1) == 2)
            &&
            (board.getBoard().get(y1).get(x1).getHasMoved() == false)
            &&
            (MoveValidator.isValidTarget(start, end, board))
        ){
            board.getBoard().get(y1).get(x1).setHasMoved(true);
            return true;

        } else if
        //for 1 square move
        (
            (MoveValidator.isVertMove(start, end, board)) 
            &&
            (MoveValidator.isValidTarget(start, end, board))
        ){
            //is it going forward?
            if(board.getActivePlayer() == "white" && (y2-y1) == -1){
                board.getBoard().get(y1).get(x1).setHasMoved(true);
                return true;
            }else if(board.getActivePlayer() == "black" && (y2-y1) == 1){
                board.getBoard().get(y1).get(x1).setHasMoved(true);
                return true;
            }
            else{
                return false;
            }
        } else if (
        //for capturing
            (Math.abs(x2-x1) == 1)
            &&
            (board.getBoard().get(y2).get(x2) != null)
            &&
            (board.getBoard().get(y2).get(x2).getPlayer() != board.getActivePlayer())
        ) {
            //is it going forward?
            if(board.getActivePlayer() == "white" && (y2-y1) == -1){
                return true;
            }else if(board.getActivePlayer() == "black" && (y2-y1) == 1){
                return true;
            }
            else{
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean isValidMove(String currentCoord, String targetCoord, Board board){
        int[] start = Board.stringToCoord(currentCoord);
        int[] end = Board.stringToCoord(targetCoord);
        String currentPlayer = board.getActivePlayer();

        int x1 = start[0];
        int y1 = start[1];

        Piece piece = board.getBoard().get(y1).get(x1);

        if (piece == null){
            return false;
        }

        if (piece.getPlayer() == currentPlayer){
            if (piece.getName() == "k"){
                return MoveValidator.isValidKing(start, end, board);

            } else if(piece.getName() == "q"){
                return MoveValidator.isValidQueen(start, end, board);

            } else if(piece.getName() == "r"){
                return MoveValidator.isValidRook(start, end, board);

            } else if(piece.getName() == "b"){
                return MoveValidator.isValidBishop(start, end, board);
                
            } else if(piece.getName() == "n"){
                return MoveValidator.isValidKnight(start, end, board);

            } else if(piece.getName() == "p"){
                return MoveValidator.isValidPawn(start, end, board);

            } else {
                return false;
            }
        } else {
            return false;
        }
        
    }

    public static Integer tryParseInt(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null; 
        }
    }

    public static boolean isValidCoord(String coords){
        String x1 = coords.substring(0,1);
        String y1 = coords.substring(1,2);
        String x2 = coords.substring(2,3);
        String y2 = coords.substring(3,4);

        if(
            coords.length() == 4 &&
            tryParseInt(x1) == null &&
            tryParseInt(y1) != null && tryParseInt(y1) <= 8 &&
            tryParseInt(x2) == null &&
            tryParseInt(y2) != null&& tryParseInt(y2) <= 8
        ){
            return true;
        }
        
        return false;
    }
}

