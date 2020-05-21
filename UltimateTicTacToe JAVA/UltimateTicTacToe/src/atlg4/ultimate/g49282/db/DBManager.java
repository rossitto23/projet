package atlg4.ultimate.g49282.db;

import atlg4.ultimate.g49282.exception.DBException;
import atlg4.ultimate.g49282.exception.DTOException;
import atlg4.ultimate.g49282.pers.PlayerDto;
import atlg4.ultimate.g49282.pers.PlayerSel;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Nicolas Rossitto, <49282@etu.he2b.be>
 */
public class DBManager {

    private static Connection connection;

    public DBManager() {
        try {
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/UltimateTT", "xxx", "xxx");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("ERROR: DATA NOT OPENED");
        }

    }

    public boolean registerPlayer(String player) {

        boolean registered = false;
        if (!player.equals("") && !isRegisterPlayer(player)) {
            try {
                
                String query = "insert into PLAYER(NAME_PLAYER)values((?))";
                PreparedStatement statt = connection.prepareStatement(query);
                statt.setString(1, player);
                int a = statt.executeUpdate();
                if (a > 0) {
                    registered = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return registered;
    }

    public static Connection getConnection() {
        return connection;
    }

    public boolean isRegisterPlayer(String player) {
        boolean isRegistered = false;
        String query = "Select * from PLAYER ";
        try {

            Statement stat = connection.createStatement();

            ResultSet resultSet = stat.executeQuery(query);

            while (resultSet.next()) {
                if (resultSet.getString("NAME_PLAYER") == player) {
                    isRegistered = true;
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isRegistered;
    }

    public ObservableList<String> getAllRegistredPlayer() {
        ObservableList<String> listString = FXCollections.observableArrayList();

        String query = "Select * from PLAYER ";
        try {

            Statement stat = connection.createStatement();

            ResultSet resultSet = stat.executeQuery(query);

            while (resultSet.next()) {
                listString.add(resultSet.getString("NAME_PLAYER"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listString;
    }

    public ObservableList<History> createHistorySelection(String player1, String player2) {

        ObservableList<History> historySelection = FXCollections.observableArrayList();
        String query = null;
        if (player2 == null || player1 == null || player2.equals(player1)) {
            query = "Select * from HISTORY where Player1 ='" + player1 + "'or Player2 ='" + player1 + "'";
        } else {
            query = "Select * from HISTORY where Player1 ='" + player1 + "' and Player2 ='" + player2 + "' "
                    + "or Player1 ='" + player2 + "' and Player2 ='" + player1 + "'";
        }

        try {
            Statement stat = connection.createStatement();
            ResultSet resultSet = stat.executeQuery(query);
            while (resultSet.next()) {
                String p1 = resultSet.getString("Player1");
                String p2 = resultSet.getString("Player2");
                int wins = resultSet.getInt("Wins");
                int loses = resultSet.getInt("Loses");
                int draw = resultSet.getInt("Draw");
                historySelection.add(new History(p1, p2, wins, loses, draw));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return historySelection;
    }

    public void updateDb(PlayerDto cli) throws DBException {

        try {

            java.sql.PreparedStatement update;
            String sql = "Update PLAYER set "
                    + "WINS=?,"
                    + "DRAW=?, "
                    + "LOSS=? "
                    + "where NAME_PLAYER=?";

            update = connection.prepareStatement(sql);
            System.out.println("lol");
            update.setInt(1, cli.getNbWin());
            update.setInt(2, cli.getNbExAequo());
            update.setInt(3, cli.getNbDefeat());
            update.setString(4, cli.getPseudo());

            update.executeUpdate();
        } catch (Exception ex) {
            throw new DBException("Player, modification impossible:\n" + ex.getMessage());
        }
    }

    public List<PlayerDto> getAllPlayers() throws DBException, DTOException {
        List<PlayerDto> clients = getCollection(new PlayerSel(0));
        return clients;
    }

    public List<PlayerDto> getCollection(PlayerSel sel) throws DBException, DTOException {
        List<PlayerDto> al = new ArrayList<PlayerDto>();
        try {
            String query = "Select NAME_PLAYER, WINS, DRAW, LOSS FROM PLAYER";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";

            if (sel.getPseudo() != null && !sel.getPseudo().equals("")) {

                where = where + " NAME_PLAYER like ? ";
            }
            if (sel.getNbWin() != 0) {
                if (!where.equals("")) {
                    where = where + " AND ";
                }
                where = where + " WINS = ? ";
            }

            if (sel.getNbExAequo() != 0) {
                if (!where.equals("")) {
                    where = where + " AND ";
                }
                where = where + " DRAW = ? ";
            }

            if (sel.getNbDefeat() != 0) {
                if (!where.equals("")) {
                    where = where + " AND ";
                }
                where = where + " LOSS = ? ";
            }

            if (where.length() != 0) {
                where = " where " + where + " order by id";
                query = query + where;
                stmt = connexion.prepareStatement(query);
                int i = 1;
                if (sel.getId() != 0) {
                    stmt.setInt(i, sel.getId());
                    i++;

                }
                if (sel.getPseudo() != null && !sel.getPseudo().equals("")) {
                    stmt.setString(i, sel.getPseudo());
                    i++;
                }
                if (sel.getNbWin() != 0) {
                    stmt.setInt(i, sel.getNbWin());
                    i++;
                }

                if (sel.getNbExAequo() != 0) {
                    stmt.setInt(i, sel.getNbExAequo());
                    i++;
                }

                if (sel.getNbDefeat() != 0) {
                    stmt.setInt(i, sel.getNbDefeat());
                    i++;
                }
            } else {
                query = query + " order by NAME_PLAYER";
                stmt = connexion.prepareStatement(query);

            }
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new PlayerDto(rs.getString("NAME_PLAYER"), rs.getInt("WINS"),
                        rs.getInt("DRAW"), rs.getInt("LOSS")));

            }
        } catch (java.sql.SQLException eSQL) {
            throw new DBException("Instanciation de Player impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }

}
