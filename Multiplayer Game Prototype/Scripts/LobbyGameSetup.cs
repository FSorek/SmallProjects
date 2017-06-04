using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.Networking;

public class LobbyGameSetup : NetworkBehaviour {
    //HOST STUFF
    [System.Serializable]
    public class HostSettings
    {
        public InputField RespawnTimeSlider;
       
        [HideInInspector]public string RespawnTimeValue;
    }
    public HostSettings hostSettings;

    public MatchSettings settings;

    private NetworkPlayer hostPlayer;
    public void clientSetup()
    {
        if (!isLocalPlayer)
            return;
        
    }
	public void HostSetup(NetworkPlayer _host)
    {
        hostPlayer = _host;
        if (Network.player == hostPlayer)
        {
            hostSettings.RespawnTimeSlider.interactable = true;
            //hostSettings.RespawnTimeSlider.text = "3";
        }
    }

    public void setGameSettings()
    {
        settings.respawnTime = int.Parse(hostSettings.RespawnTimeSlider.text);
    }

}
