package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.model.MovePayload;

@Service
public class GameService {

	private Map<Integer,int[][]> gameList= new HashMap<Integer, int[][]>(); 
	private Map<Integer,Integer> gameNextDot= new HashMap<Integer, Integer>();
	private Map<Integer,Integer> gameListCounter= new HashMap<Integer,Integer>();
	
	private int index=1;
	
	public String move(MovePayload movePayload) {
		
		switch(movePayload.getMove().toLowerCase()){
			case "start":{
				return "Game started with GameId:" +startGame()+ " use this id for all next moves for this game.";
			}
			case "move":{
				int gameId=movePayload.getId();
				if( gameId!=0 &&  gameList.containsKey(gameId)) {
					if(gameListCounter.get(gameId)>42) {
						return "Game Finished";
					}
					int player =gameNextDot.get(movePayload.getId());
					int[][] board= gameList.get(movePayload.getId());
					if(movePayload.getCol()>7 || movePayload.getCol()<=0) {
						return "Invalid Move";
					}
					if(!newMove(movePayload.getCol()-1, board, player)) {
						return "Invalid Move";
					}
					if(checkBoard(board)) {
						gameListCounter.put(gameId,43);
						return player==1?"Yellow Wins":"Red Wins";
					}
					switchNextDot(gameId);
					if(gameListCounter.get(gameId)>=42) {
						return "Draw Game";
					}
				}
				else {
					return "Invalid Game";
				}
				return "Valid Move";
				
			}
			default:{
				return "Invalid Move"; 
			}
		}
	}
	
	private boolean newMove(int col, int[][] board,int user) {
		for(int i=0; i <6; i++) {
			if(board[i][col]==0) {
				board[i][col]= user;
				return true;
			}
		}
		return false;
	}
	
	private boolean checkBoard(int[][] board) {
		for(int i=1; i <3;i++) {
			for(int r=0;r<6;r++) {
				for(int c=0;c<4;c++) {
					if (board[r][c] == i && board[r][c+1] == i && board[r][c+2]	== i && board[r][c+3] == i) {
						return true;
					}
				}
			}
			
			
	
			 for (int r=0; r<3;r++) {
				 for (int c=0; c<7;c++) {
					 if (board[r][c] == i && board[r+1][c] == i && board[r+2][c]== i && board[r+3][c] == i) {
						 return true;
					 }
				 }
			 
			 }
			 
			 for(int r=3;r<6;r++) {
				 for(int c=0;c<4;c++) {
					 if (board[r][c] == i && board[r-1][c+1] == i && board[r-2][c+2] == i && board[r-3][c+3] == i) {
						 return true;
					 }
					 
				 }
			 }
			 
			 for(int r=0;r<3;r++) {
					 for(int c=0;c<4;c++) {
					 if (board[r][c] == i && board[r+1][c+1] == i && board[r+2][c+2] == i && board[r+3][c+3] == i){
						 return true;
					}
				}
			}
		}
		 
		 return false;
		
	}
	
	private void switchNextDot(int id) {
		gameNextDot.put(id,gameNextDot.get(id)==1?2:1);
		gameListCounter.put(id,gameListCounter.get(id)+1);
	}
	
	private int startGame() {
		gameList.put(index, new int[6][7] );
		gameNextDot.put(index,1);
		gameListCounter.put(index,0);
		index++;
		return index-1;
	}
}
