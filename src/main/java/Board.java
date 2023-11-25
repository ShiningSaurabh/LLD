public class Board {
    int size;
    PlayingPiece board[][];
    Board(int size){
        this.size=size;
        board=new PlayingPiece[size][size];
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public PlayingPiece[][] getBoard() {
        return board;
    }

    public void setBoard(PlayingPiece[][] board) {
        this.board = board;
    }
    public void addPieceAtCell(int i,int j,PlayingPiece p){
        this.board[i][j]=p;
    }
    public boolean checkIfACellIsVacant(int i,int j){
        if(board[i][j]==null)
            return true;
        return false;
    }
    public boolean checkIfBoardHasAnEmptyCell(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(!checkIfACellIsVacant(i,j))
                    return false;
            }
        }
        return true;
    }

    public PlayingPiece getPieceAtACell(int i, int j){
        if(!checkIfACellIsVacant(i,j))
            return this.board[i][j];
        return null;
    }
}
