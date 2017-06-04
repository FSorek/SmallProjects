using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;

[RequireComponent(typeof(Player))]
public class PlayerSetup : NetworkBehaviour {      
    [SerializeField]
    private Behaviour[] Behaviours;
    [SerializeField]
    private string remoteLayerName = "RemotePlayer";
    private Camera sceneCamera;

    [SerializeField]
    private GameObject playerUIPrefab;
    private GameObject playerUIInstance;

    [SerializeField]
    private AudioSource audioPlayer;
    [SerializeField]
    private AudioSource effectsAudioPlayer;
    // Use this for initialization
    void Start()
    {
        if (!isLocalPlayer)
        {
            DisableComponents();
            AssingRemoteLayer();
        }
        else
        {
            audioPlayer.volume = 0.005f;
            audioPlayer.spatialBlend = 0;
            audioPlayer.priority = 256;
            sceneCamera = Camera.main;
            if(sceneCamera!=null)
            {
                sceneCamera.gameObject.SetActive(false);
            }

            //Create Player UI
            playerUIInstance = Instantiate(playerUIPrefab);
            playerUIInstance.name = playerUIPrefab.name;

            playerUIInstance.GetComponent<PlayerUI>().SetPlayer(GetComponent<Player>());
        }
        GetComponent<Player>().Setup();
        
    }

    public override void OnStartClient()
    {
        base.OnStartClient();

        string _netID = GetComponent<NetworkIdentity>().netId.ToString();
        Player _player = GetComponent<Player>();
        GameManager.RegisterPlayer(_netID, _player);
    }

    void AssingRemoteLayer()
    {
        gameObject.layer = LayerMask.NameToLayer(remoteLayerName);
    }
    void DisableComponents()
    {
        foreach (Behaviour beh in Behaviours)
        {
            beh.enabled = false;
        }
    }

    private void OnDisable()
    {
        Destroy(playerUIInstance);
        if(sceneCamera!=null)
        {
            sceneCamera.gameObject.SetActive(true);
        }
        GameManager.UnRegisterPlayer(transform.name);
    }

    public PlayerUI getPlayerUI() { return playerUIInstance.GetComponent<PlayerUI>(); }
}
