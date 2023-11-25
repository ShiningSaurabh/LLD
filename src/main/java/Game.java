import java.util.*;

public class Game {
    Board board;
    Deque<Player>players = new ArrayDeque<Player>();
    Game(int n, String firstPlayerName,String secondPlayerName){
        this.initializeGame(n,firstPlayerName,secondPlayerName);
        this.startGame();
    }

    private String startGame() {
        Boolean winnerFlag=false;
        while(!winnerFlag){
            Player currentPlayer=players.removeFirst();
            PlayingPiece currentPiece=currentPlayer.getPiece();
            if(!board.checkIfBoardHasAnEmptyCell()){ // Tie case
                winnerFlag=true;
                continue;
            }
            Integer posI,posJ;
            Scanner scanner=new Scanner(System.in);
            String input=scanner.nextLine();
            String[] inputArr=input.split(",");
            posI= Integer.valueOf(inputArr[0]);
            posJ=Integer.valueOf(inputArr[1]);
            if(posI<0 || posI>=this.board.size || posJ<0 || posJ>=this.board.size || !board.checkIfACellIsVacant(posI,posJ)){
                System.out.println("Wrong input");
                players.addFirst(currentPlayer);
                continue;
            }
            board.addPieceAtCell(posI,posJ,currentPiece);
            boolean winner = checkIfWeHaveAWinner(currentPiece);
            if(winner)
                return currentPlayer.getName();
            players.addLast(currentPlayer);

        }
        return "tie";
    }

    private boolean checkIfWeHaveAWinner(PlayingPiece currentPiece){
        boolean rowMatchFlag=false;
        boolean columnMatchFlag=false;
        boolean diagonalMatchFlag=false;

        for(int i=0;i<board.size;i++){
            rowMatchFlag=false;
            int j=0;
            for(;j< board.size;j++){
                if(board.checkIfACellIsVacant(i,j))
                    break;
                if(board.getPieceAtACell(i,j)!=currentPiece)
                    break;
            }
            if(j== board.size) {
                rowMatchFlag = true;

            }
        }
        if(rowMatchFlag)
        return rowMatchFlag;

        for(int i=0;i<board.size;i++){
            columnMatchFlag=false;
            int j=0;
            for(;j< board.size;j++){
                if(board.checkIfACellIsVacant(j,i))
                    break;
                if(board.getPieceAtACell(j,i)!=currentPiece)
                    break;
            }
            if(j == board.size) {
                columnMatchFlag = true;

            }
        }
        if(columnMatchFlag)
            return columnMatchFlag;

        for(int i=0;i< board.size;i++){
            if(board.checkIfACellIsVacant(i,i))
                    break;
            if(board.getPieceAtACell(i,i)!= currentPiece)
                break;
            if(i==board.size)
                diagonalMatchFlag=true;
        }
        if(diagonalMatchFlag)
            return diagonalMatchFlag;
        return false;
    }

    private void initializeGame(int n, String firstPlayerName,String secondPlayerName) {
        board= new Board(n);
        PlayingPiece X = new PlayingPieceX(Piece.X);
        PlayingPiece O = new PlayingPieceO(Piece.O);
        Player p1=new Player(firstPlayerName,X);
        Player p2=new Player(secondPlayerName,O);
        this.players= new ArrayDeque<Player>();
//        players= ;
        this.players.addFirst(p1);
        this.players.addLast(p2);

    }

}
