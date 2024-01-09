package com.exercise1.lab5;
/*Author: Najmun Nahar
 * Lab Assignment-5
 * Course: COMP228-004
 * Date: 30-07-2021
 */
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class OnlineGame extends Application {
	
	//Declaring Variables
	private Vector rows;
	private Vector columns;
	String result;
	
	private void createAndSetSwingContent(final SwingNode swingNode, JScrollPane jsp) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                swingNode.setContent(jsp);
            }
        });
    }
	//method for database connection
	private Connection connect()
	{
		// create a connection to the playergame.db database
		String url="jdbc:sqlite:playergame.db";
		Connection connection=null;
		try 
		{	
			connection=DriverManager.getConnection(url);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return connection;
	}
	//Method For Creating Database Tables
	public void createTable()
	{
		try(Connection connection=this.connect();Statement statement = connection.createStatement())
		{
			 // set timeout to 30 sec.
			statement.setQueryTimeout(30);	
			
			//create database table named Game
			statement.executeUpdate("drop table if exists Game");
			statement.executeUpdate("create table Game(game_id integer primary key autoincrement,game_title string)");
			
			//create database table named Player
			statement.executeUpdate("drop table if exists Player");
			statement.executeUpdate("create table Player(player_id integer primary key autoincrement,first_name string,last_name string,address string,postal_code string,province string,phone_number string)");
			
			//create database table named PlayerAndGame
			statement.executeUpdate("drop table if exists PlayerAndGame");
			statement.executeUpdate("create table PlayerAndGame(player_game_id integer primary key autoincrement,game_id integer,player_id integer,playing_date date,score integer)" );
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	//Method to insert data in Player Table
	public void insertPlayer(String fname,String lname,String address,String postal,String prov,String phone)
	{
		
		String sql="insert into Player values(?,?,?,?,?,?,?)";
		try(Connection connection=this.connect();PreparedStatement pstmt=connection.prepareStatement(sql))
		{
			//Setting values using prepared statements
			pstmt.setString(2, fname);
			pstmt.setString(3, lname);
			pstmt.setString(4, address);
			pstmt.setString(5, postal);
			pstmt.setString(6, prov);
			pstmt.setString(7, phone);
			pstmt.executeUpdate();	
		}
		catch(Exception er)
		{
			er.printStackTrace();
		}
	}
	//Method for Inserting Data into Game Table
	public void insertGame(String gameTitle)
	{
		//Insert records into table Game
		String sql="insert into Game values(?,?)";
		try(Connection connection=this.connect();PreparedStatement pstmt=connection.prepareStatement(sql))
		{
			pstmt.setString(2, gameTitle);
			pstmt.executeUpdate();	
		}
		catch(Exception er)
		{
			er.printStackTrace();
		}
	}
	//Method for Inserting Data into PlayerAndGame Table
	public void insertPlayerAndGame(int gameId,int playerId,LocalDate date,int score)
	{
		//Insert records into table PlayerAndGame
		String sql="insert into PlayerAndGame values(?,?,?,?,?)";
		try(Connection connection=this.connect();PreparedStatement pstmt=connection.prepareStatement(sql))
		{
			
			pstmt.setInt(2, gameId);
			pstmt.setInt(3, playerId);
			pstmt.setObject(4, date);
			pstmt.setInt(5, score);
			pstmt.executeUpdate();	
		}
		catch(Exception er)
		{
			er.printStackTrace();
		}
	}
	//Method for Updating data in the database tables
	public void update(int id,String fname,String lname,String address,String postal,String prov,int phone)
	{
		String sql="update Player set first_name=?,last_name=?,address=?,postal_code=?,province=?,phone_number=? where player_id=?";
		try(Connection connection=this.connect();PreparedStatement pstmt=connection.prepareStatement(sql))
		{			
			pstmt.setString(1, fname);
			pstmt.setString(2, lname);
			pstmt.setString(3, address);
			pstmt.setString(4, postal);
			pstmt.setString(5, prov);
			pstmt.setInt(6, phone);
			pstmt.setInt(7, id);
			pstmt.executeUpdate();	
		}
		catch(Exception er)
		{
			er.printStackTrace();
		}
	}
	//Method for displaying resultsets
	public void selectAll(String sqlQuery)
	{
		rows=new Vector();
		columns=new Vector();
		try(Connection connection=this.connect();Statement statement = connection.createStatement();
				ResultSet resultset=statement.executeQuery(sqlQuery))
		{			
			ResultSetMetaData metadata=resultset.getMetaData();
			
			int num=metadata.getColumnCount();
			//create columns headers
			for(int i=1;i<=num;i++)
			{
				columns.addElement(metadata.getColumnName(i));
			}
			int row=0;
			while(resultset.next())
			{
				Vector vRow = new Vector();
				for(int i=1;i<=num;i++)
				{
					vRow.addElement( resultset.getObject(i).toString() );
				}
				row = row + 1;
				rows.addElement(vRow);
			}
			
			resultset.close();
		}
		catch(Exception er)
		{
			er.printStackTrace();
		}
	}
	//Method to display Player Id
	public void select(String sql)
	{
		try(Connection connection=this.connect();Statement statement = connection.createStatement();
				ResultSet resultset=statement.executeQuery(sql))
		{			
			ResultSetMetaData metadata=resultset.getMetaData();		
			int num=metadata.getColumnCount();
			
			while(resultset.next())
			{
				for(int i=1;i<=num;i++)
				{
					result=resultset.getObject(i).toString();
				}

			}
			
			resultset.close();
		}
		catch(Exception er)
		{
			er.printStackTrace();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception{
		
		//Pane for the Home page of the game app
		GridPane pane=new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setVgap(10);
		pane.setPrefSize(700, 500);
		
		//Insert button for Player Table
		Button btn1=new Button("Insert Player");
		btn1.setPrefSize(150, 35);
		
		//Insert button for Game Table
		Button btn2=new Button("Insert Game");
		btn2.setPrefSize(150, 35);
		
		//Insert button for PlayerAndGame Table
		Button btn3=new Button("Map Player and Game");
		btn3.setPrefSize(150, 35);
		
		//Update button for Player Table
		Button btn4=new Button("Update Player");
		btn4.setPrefSize(150, 35);
		
		//Display button to display records
		Button btn5=new Button("Display Reports");
		btn5.setPrefSize(150, 35);
		
		pane.add(btn1, 0, 0);
		pane.add(btn2, 0, 1);
		pane.add(btn3, 0, 2);
		pane.add(btn4, 0, 3);
		pane.add(btn5, 0, 4);
	
		//Pane for Player Table
		BorderPane paneForPlayer=new BorderPane();
		paneForPlayer.setPrefSize(900,450);

		GridPane paneForText=new GridPane();
		paneForText.setVgap(25);
		paneForText.setHgap(40);
		paneForText.setPadding(new Insets(15,15,15,15));
		
		TextField plTf1=new TextField();//to enter first name
		TextField plTf2=new TextField();//to enter last name
		TextField plTf3=new TextField();//to enter the address
		TextField plTf4=new TextField();//to enter postal code
		TextField plTf5=new TextField();//to enter province
		TextField plTf6=new TextField();//to enter phone number
		
		Button plBtn=new Button("Insert");
		Button plBtn2=new Button("Back");
		plBtn.setPrefSize(150, 40);
		plBtn2.setPrefSize(150, 40);
		
		paneForText.add(new Label("First Name:"), 0, 0);
		paneForText.add(new Label("Last Name:"), 0, 1);
		paneForText.add(new Label("Address:"), 0, 2);
		paneForText.add(new Label("Postal Code:"), 0, 3);
		paneForText.add(new Label("Province:"), 0, 4);
		paneForText.add(new Label("Phone Number:"), 0, 5);
				
		paneForText.add(plTf1, 1, 0);
		paneForText.add(plTf2, 1, 1);
		paneForText.add(plTf3, 1, 2);
		paneForText.add(plTf4, 1, 3);
		paneForText.add(plTf5, 1, 4);
		paneForText.add(plTf6, 1, 5);
		
		paneForText.add(plBtn2, 0, 6);
		paneForText.add(plBtn, 1, 6);
		
		//Pane for JTable for Player
		GridPane paneForTable=new GridPane();
		//Create a model
		DefaultTableModel model = new DefaultTableModel();
		JTable table = new JTable(model);
		table.setRowHeight(30);
		JScrollPane scrollPane= new JScrollPane(table); 
		scrollPane.setPreferredSize( new Dimension(900, 340) );
		
		SwingNode swingNode = new SwingNode();
		createAndSetSwingContent(swingNode, scrollPane);
		paneForTable.add(swingNode, 0, 0);
		paneForTable.setPadding(new Insets(15,15,15,15));	
		
		paneForPlayer.setLeft(paneForText);
		paneForPlayer.setRight(paneForTable);
						
		//Pane for JTable for Game
		BorderPane paneForGame=new BorderPane();
		paneForGame.setPrefSize(700, 300);
		
		BorderPane paneForCenter=new BorderPane();
		
		GridPane paneForGameTitle=new GridPane();
		paneForGameTitle.setPadding(new Insets(10,10,10,10));
		paneForGameTitle.setHgap(15);
		paneForGameTitle.setAlignment(Pos.CENTER);
		
		TextField gametf=new TextField();
		paneForGameTitle.add(new Label("Game Title:"), 0, 0);
		paneForGameTitle.add(gametf, 1, 0);
		
		GridPane paneForButtons=new GridPane();
		paneForButtons.setHgap(15);
		paneForButtons.setPadding(new Insets(10,10,10,10));
		Button insertBtn=new Button("Insert");
		insertBtn.setPrefSize(150, 30);
			
		Button backBtn=new Button("Back");
		backBtn.setPrefSize(150, 30);
				
		paneForButtons.add(backBtn, 0, 1);
		paneForButtons.add(insertBtn, 1, 1);
		
		paneForCenter.setCenter(paneForGameTitle);
		paneForCenter.setBottom(paneForButtons);	
		//Create a model
		DefaultTableModel model2 = new DefaultTableModel();
		JTable table2 = new JTable(model2);
		table2.setRowHeight(30);
		JScrollPane scrollPane2= new JScrollPane(table2); 
		
		SwingNode swingNode2 = new SwingNode();
		createAndSetSwingContent(swingNode2, scrollPane2);
		paneForGame.setPadding(new Insets(15,15,15,15));
			
		paneForGame.setLeft(paneForCenter);
		paneForGame.setRight(swingNode2);
		
		//Pane for PlayerAndGame 
		BorderPane paneForGamePlayer=new BorderPane();
		paneForGamePlayer.setPrefSize(700, 300);
		
		GridPane paneForLeftArea=new GridPane();
		paneForLeftArea.setHgap(20);
		paneForLeftArea.setVgap(20);
		paneForLeftArea.setPadding(new Insets(15,15,15,15));	
		
		ComboBox cb1=new ComboBox();//to select player id
		ComboBox cb2=new ComboBox();//to select game id
		cb1.setPrefSize(175, 20);
		cb2.setPrefSize(175, 20);
		
		DatePicker dateTf=new DatePicker();//to select the date
		TextField scoreTf=new TextField();//to enter the score
		
		Button insertBtn2=new Button("Insert");//insert into Game Table
		Button backBtn2=new Button("Back");//to go back to the home page
		insertBtn2.setPrefSize(175, 35);
		backBtn2.setPrefSize(175, 35);
		
		//add all elements in the pane
		paneForLeftArea.add(new Label("Select Player Id:"), 0, 0);
		paneForLeftArea.add(cb1, 1, 0);
		paneForLeftArea.add(new Label("Select Game Id:"), 0, 1);
		paneForLeftArea.add(cb2, 1, 1);
		paneForLeftArea.add(new Label("Playing Date:"), 0, 2);
		paneForLeftArea.add(dateTf, 1, 2);
		paneForLeftArea.add(new Label("Score:"), 0, 3);
		paneForLeftArea.add(scoreTf, 1, 3);
		paneForLeftArea.add(insertBtn2, 0, 4);
		paneForLeftArea.add(backBtn2, 1, 4);
		
		//Pane for JTable for PlayerAndGame
		GridPane paneForRightArea=new GridPane();
		//create model
		DefaultTableModel model3 = new DefaultTableModel();
		JTable table3 = new JTable(model3);
		table3.setRowHeight(30);
		JScrollPane scrollPane3= new JScrollPane(table3); 
		scrollPane3.setPreferredSize(new Dimension(500,300));
		
		SwingNode swingNode3 = new SwingNode();
		createAndSetSwingContent(swingNode3, scrollPane3);
		paneForRightArea.add(swingNode3, 0, 0);
		paneForRightArea.setPadding(new Insets(15,15,15,15));

				
		paneForGamePlayer.setLeft(paneForLeftArea);
		paneForGamePlayer.setRight(paneForRightArea);
		
		//Pane For Update Player
		BorderPane paneForUpdatePlayer=new BorderPane();
				
		GridPane paneForInputArea=new GridPane();
		paneForInputArea.setVgap(25);
		paneForInputArea.setHgap(40);
		paneForInputArea.setPadding(new Insets(15,15,15,15));
		
		ComboBox cb3=new ComboBox();//to select player id
		cb3.setPrefSize(150, 20);
		TextField text1=new TextField();//to enter player first name
		TextField text2=new TextField();//to enter player last name
		TextField text3=new TextField();//to enter address
		TextField text4=new TextField();//to enter postal code
		TextField text5=new TextField();//to enter province
		TextField text6=new TextField();//to enter phone number
		
		Button upBtn=new Button("Update");//to update data
		Button bkBtn=new Button("Back");//to go back
		upBtn.setPrefSize(150, 40);
		bkBtn.setPrefSize(150, 40);
		//add all the elements in the pane
		paneForInputArea.add(new Label("Select Player Id:"), 0, 0);
		paneForInputArea.add(new Label("First Name:"), 0, 1);
		paneForInputArea.add(new Label("Last Name:"), 0, 2);
		paneForInputArea.add(new Label("Address:"), 0, 3);
		paneForInputArea.add(new Label("Postal Code:"), 0, 4);
		paneForInputArea.add(new Label("Province:"), 0, 5);
		paneForInputArea.add(new Label("Phone Number:"), 0, 6);
		
		paneForInputArea.add(cb3, 1, 0);
		paneForInputArea.add(text1, 1, 1);
		paneForInputArea.add(text2, 1, 2);
		paneForInputArea.add(text3, 1, 3);
		paneForInputArea.add(text4, 1, 4);
		paneForInputArea.add(text5, 1, 5);
		paneForInputArea.add(text6, 1, 6);
		
		paneForInputArea.add(upBtn, 0, 7);
		paneForInputArea.add(bkBtn, 1, 7);
		//Pane for JTable of Updated table
		GridPane paneForRight=new GridPane();
		
		DefaultTableModel model4 = new DefaultTableModel();
		JTable table4 = new JTable(model4);
		JScrollPane scrollPane4= new JScrollPane(table4); 
		
		SwingNode swingNode4 = new SwingNode();
		createAndSetSwingContent(swingNode4, scrollPane4);
		paneForRight.add(swingNode4, 0, 0);
		paneForRight.setPadding(new Insets(15,15,15,15));
				
		paneForUpdatePlayer.setLeft(paneForInputArea);
		paneForUpdatePlayer.setRight(paneForRight);
		
		
		//Display Records JTable
		GridPane paneForDisplay=new GridPane();
		
		DefaultTableModel model5 = new DefaultTableModel();
		JTable table5 = new JTable(model5);
		JScrollPane scrollPane5= new JScrollPane(table5); 
		scrollPane5.setPreferredSize(new Dimension(1200,340));
		
		SwingNode swingNode5 = new SwingNode();
		createAndSetSwingContent(swingNode5, scrollPane5);
		paneForDisplay.add(swingNode5, 0, 0);
		paneForDisplay.setPadding(new Insets(15,15,15,15));
		
		//Back Button
		Button bkBtn2=new Button("Back");
		bkBtn2.setPrefSize(150, 40);
		paneForDisplay.add(bkBtn2, 0, 1);
		
		//Display Pane
		BorderPane paneForDisplayArea=new BorderPane();
		
		GridPane paneForDisplayBtns=new GridPane();
		paneForDisplayBtns.setAlignment(Pos.CENTER);
		paneForDisplayBtns.setHgap(20);
		paneForDisplayArea.setPrefSize(700, 500);
		
		ComboBox cb4=new ComboBox();
		cb4.setPrefSize(150, 20);
		
		Button displayBtn1=new Button("Display");
		displayBtn1.setPrefSize(150, 35);
				
		
		paneForDisplayBtns.add(new Label("Select Player Id:"), 0, 0);
		paneForDisplayBtns.add(cb4, 1, 0);
		paneForDisplayBtns.add(displayBtn1, 2, 0);
		
		GridPane paneForBottombtn=new GridPane();	
		paneForBottombtn.setAlignment(Pos.CENTER);
		paneForBottombtn.setHgap(20);
		paneForBottombtn.setPadding(new Insets(15,15,15,15));
		
		Button displayBackBtn=new Button("Back");
		displayBackBtn.setPrefSize(150, 35);
		
		Button displayBtn2=new Button("Display all Records");
		displayBtn2.setPrefSize(150, 35);
		
		paneForBottombtn.add(displayBtn2, 1, 0);
		paneForBottombtn.add(displayBackBtn, 0, 0);
		
		paneForDisplayArea.setCenter(paneForDisplayBtns);
		paneForDisplayArea.setBottom(paneForBottombtn);
		
		//Create scenes
		Scene scene=new Scene(pane);
		Scene scene2=new Scene(paneForPlayer);
		Scene scene3=new Scene(paneForGame);
		Scene scene4=new Scene(paneForGamePlayer);
		Scene scene5=new Scene(paneForUpdatePlayer);
		Scene scene6=new Scene(paneForDisplayArea);
		Scene scene7=new Scene(paneForDisplay);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Game App");
		primaryStage.show();
		
		//Action Event Handlers
				btn1.setOnAction(e->{
					primaryStage.setScene(scene2);
				});
				btn2.setOnAction(e->{
					primaryStage.setScene(scene3);
				});
				btn3.setOnAction(e->{
					primaryStage.setScene(scene4);
				});
				btn4.setOnAction(e->{
					primaryStage.setScene(scene5);
				});
				btn5.setOnAction(e->{
					primaryStage.setScene(scene6);
				});
				
		/*------Player Buttons------*/			
				//insert Button
				plBtn.setOnAction(e->{
					insertPlayer(plTf1.getText(),plTf2.getText(),plTf3.getText(),plTf4.getText(),plTf5.getText(),plTf6.getText());
					String sql="select * from Player";
					selectAll(sql);
					model.setDataVector(rows,columns);
					model.fireTableStructureChanged();
					
					//Player Id 
					select("select player_id from Player");
					cb1.getItems().add(result);//add player id to cb1
					cb3.getItems().add(result);//add player id to cb3
					
				});
				
				//Back Button
				plBtn2.setOnAction(e->{
					primaryStage.setScene(scene);
				});
				
		/*--------Game Buttons-------*/
				
				//insert Button
				insertBtn.setOnAction(e->{
					insertGame(gametf.getText());
					String sql="select * from Game";
					selectAll(sql);
					model2.setDataVector(rows,columns);
					model2.fireTableStructureChanged();
					select("select game_id from Game");
					cb2.getItems().add(result);
				});
				
				//Back Button
				backBtn.setOnAction(e->{
					primaryStage.setScene(scene);
				});
				
		/*-------PlayerAndGame Buttons-----*/
				//insert button
				insertBtn2.setOnAction(e->{
					int gameId=Integer.parseInt(cb2.getValue().toString());
					int playerId=Integer.parseInt(cb1.getValue().toString());
					int score=Integer.parseInt(scoreTf.getText());
					insertPlayerAndGame(gameId,playerId,dateTf.getValue(),score);
					String sql="select * from PlayerAndGame";
					selectAll(sql);
					model3.setDataVector(rows,columns);
					model3.fireTableStructureChanged();
							
					//Player Id 
					select("select player_id from PlayerAndGame");
					if(!cb4.getItems().contains(result))
					{
						cb4.getItems().add(result);
					}
					
				});
				//Back button
				backBtn2.setOnAction(e->{
					primaryStage.setScene(scene);
				});
				
		/*------Update Player Buttons--------*/
				//update button
				upBtn.setOnAction(e->{
					int id=Integer.parseInt(cb3.getValue().toString());
					update(id,text1.getText(),text2.getText(),text3.getText(),text4.getText(),text5.getText(),Integer.parseInt(text6.getText()));
					String sql="select * from Player";
					selectAll(sql);
					model4.setDataVector(rows,columns);
					model4.fireTableStructureChanged();
					
					//Player Table
					model.setDataVector(rows,columns);
					model.fireTableStructureChanged();
					//Display table
					String sql2="select a.player_id,a.first_name,a.last_name,a.address,a.postal_code,a.province,a.phone_number,b.player_game_id,b.game_id,b.game_title,b.playing_date,b.score from Player a,(select c.player_game_id,c.game_id,c.player_id,c.playing_date,c.score,d.game_title from PlayerAndGame c,Game d where c.game_id=d.game_id)b where a.player_id=b.player_id";
					selectAll(sql2);
					model5.setDataVector(rows,columns);
					model5.fireTableStructureChanged();
					
				});
				//Back button
				bkBtn.setOnAction(e->{
					primaryStage.setScene(scene);
				});
				
		/*------Display Panel-------*/
				//Display Button
				displayBtn1.setOnAction(e->{
					int num=Integer.parseInt(cb4.getValue().toString());
					String sql2="select b.player_game_id,a.player_id,a.first_name,a.last_name,a.address,a.postal_code,a.province,a.phone_number,b.game_id,b.game_title,b.playing_date,b.score from Player a,(select c.player_game_id,c.game_id,c.player_id,c.playing_date,c.score,d.game_title from PlayerAndGame c,Game d where c.game_id=d.game_id)b where a.player_id=b.player_id and a.player_id="+num;
					selectAll(sql2);
					model5.setDataVector(rows,columns);
					model5.fireTableStructureChanged();
					primaryStage.setScene(scene7);
				});
				//Display all records button
				displayBtn2.setOnAction(e->{
					String sql2="select b.player_game_id,a.player_id,a.first_name,a.last_name,a.address,a.postal_code,a.province,a.phone_number,b.game_id,b.game_title,b.playing_date,b.score from Player a,(select c.player_game_id,c.game_id,c.player_id,c.playing_date,c.score,d.game_title from PlayerAndGame c,Game d where c.game_id=d.game_id)b where a.player_id=b.player_id";
					selectAll(sql2);
					model5.setDataVector(rows,columns);
					model5.fireTableStructureChanged();
					primaryStage.setScene(scene7);
				});
				//display panel back button
				displayBackBtn.setOnAction(e->{
					primaryStage.setScene(scene);
				});
				//Display table
				//Back button
				bkBtn2.setOnAction(e->{
					primaryStage.setScene(scene6);
				});
				
				
	}
	public static void main(String[] args) {
		OnlineGame og=new OnlineGame();
		og.createTable();
		launch(args);

	}

}
