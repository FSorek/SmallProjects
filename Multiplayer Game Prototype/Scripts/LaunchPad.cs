using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LaunchPad : MonoBehaviour {
    public float forcePower;
    public float upwardsModifier;
    private void OnCollisionEnter(Collision collision)
    {
        if(collision.collider.GetComponent<Player>())
        {
            Player player = collision.collider.GetComponent<Player>();
            player.RpcAddForce(transform.forward, upwardsModifier, forcePower);
        }
    }
}
