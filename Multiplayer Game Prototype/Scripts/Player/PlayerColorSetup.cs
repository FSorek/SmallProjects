using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;

public class PlayerColorSetup : NetworkBehaviour {
    [SyncVar]
    public Color color;
    public GameObject BaseObj;
    public GameObject SecondaryObj;
    

    private void Start()
    {
        if (isLocalPlayer)
        {
            BaseObj.GetComponent<MeshRenderer>().material.color = color;
            SecondaryObj.GetComponent<MeshRenderer>().material.color = color;
            CmdSetMeshColors(color);
        }
        else
        {
            //CmdSetMeshColors(color);
            BaseObj.GetComponent<MeshRenderer>().material.color = color;
            SecondaryObj.GetComponent<MeshRenderer>().material.color = color;
        }
    }

    public override void OnStartClient()
    {

        if (isServer)
        {
            //color = new Color(Random.Range(0.0f, 1.0f), Random.Range(0.0f, 1.0f), Random.Range(0.0f, 1.0f));
            RpcSetColor(color);
        }

    }

    [ClientRpc]
    void RpcSetColor(Color c)
    {
        color = c;
        BaseObj.GetComponent<MeshRenderer>().material.color = color;
        SecondaryObj.GetComponent<MeshRenderer>().material.color = color;
    }

    [Command]
    public void CmdSetMeshColors(Color c)
    {
        color = c;
    }
}
