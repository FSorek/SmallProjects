using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;
using UnityEngine.Networking.Match;

public class PauseMenu : MonoBehaviour {

    public static bool isOn = false;

    private NetworkLobbyManager networkManager;

    private void Start()
    {
        //networkManager = (NetworkLobbyManager)NetworkManager.singleton;
    }

    public void LeaveGame()
    {
       // MatchInfo matchInfo = networkManager.matchInfo;
       // networkManager.matchMaker.DropConnection(matchInfo.networkId, matchInfo.nodeId, 0, networkManager.OnDropConnection);
       // networkManager.StopHost();
    }
}
