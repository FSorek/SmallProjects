using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;

public class PlayerInfoHook : Prototype.NetworkLobby.LobbyHook {

    public override void OnLobbyServerSceneLoadedForPlayer(NetworkManager manager, GameObject lobbyPlayer, GameObject gamePlayer)
    {
        base.OnLobbyServerSceneLoadedForPlayer(manager, lobbyPlayer, gamePlayer);

        gamePlayer.GetComponent<Player>().nickname = lobbyPlayer.GetComponent<Prototype.NetworkLobby.LobbyPlayer>().playerName;
        gamePlayer.GetComponent<PlayerColorSetup>().color = lobbyPlayer.GetComponent<Prototype.NetworkLobby.LobbyPlayer>().playerColor;
    }
}
