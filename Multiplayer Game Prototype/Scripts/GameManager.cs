using UnityEngine;
using System.Collections.Generic;
using System.Linq;

public class GameManager : MonoBehaviour
{
    public static GameManager Instance;
    public MatchSettings matchSettings;

    public delegate void OnPlayerKilledCallback(string player, string source);
    public OnPlayerKilledCallback onPlayerKilledCallback;
    private void Awake()
    {
        if(Instance != null)
        {
            Debug.LogError("GameManager Instance Exists");
        }
        else
        {
            Instance = this;
        }
        if(Prototype.NetworkLobby.LobbyManager.s_Singleton != null)
        {
            LobbyGameSetup lobbyOptions = Prototype.NetworkLobby.LobbyManager.s_Singleton.getLobbyGameSetup();
            matchSettings = lobbyOptions.settings;
        }
    }

    #region Player Registering

    private const string PLAYER_ID_PREFIX = "Player ";
    private static Dictionary<string, Player> players = new Dictionary<string, Player>();

    public static void RegisterPlayer(string _netID, Player _player)
    {
        string _playerID = PLAYER_ID_PREFIX + _netID;
        players.Add(_playerID, _player);
        _player.transform.name = _playerID;
    }

    public static void UnRegisterPlayer(string _playerID)
    {
        players.Remove(_playerID);
    }

    public static Player GetPlayer(string _playerID)
    {
        return players[_playerID];
    }

    public static Player[] getPlayersArray()
    {
        return players.Values.ToArray();
    }
    #endregion

}
