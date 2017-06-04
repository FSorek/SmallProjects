using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class RocketProjectile : Projectile {
    [SerializeField]
    private float explosionRange = 5f;
    [SerializeField]
    private LayerMask playerLayer;
    [SerializeField]
    private float explosionForce = 1f;
    protected override void OnHitNonPlayerSpecialAction(Collider objectCollider, int damage, string ownerID)
    {
        Collider[] hitPlayers = Physics.OverlapSphere(transform.position, explosionRange, playerLayer);
        foreach (Collider playerCol in hitPlayers)
        {
            playerCol.GetComponent<Player>().RpcLaunch(explosionForce, explosionRange, transform.position, 0);
            if(playerCol.name == ownerID)
                playerCol.GetComponent<Player>().RpcTakeDamage((int)(damage / 2), ownerID);
            else
                playerCol.GetComponent<Player>().RpcTakeDamage((int)(damage), ownerID);
        }
    }

    protected override void OnHitPlayerSpecialAction(Collider objectCollider, int damage, string ownerID)
    {
        Collider[] hitPlayers = Physics.OverlapSphere(transform.position, explosionRange, playerLayer);
        foreach (Collider playerCol in hitPlayers)
        {
            playerCol.GetComponent<Player>().RpcLaunch(explosionForce, explosionRange, transform.position, 0);
            if (playerCol.name == ownerID)
                playerCol.GetComponent<Player>().RpcTakeDamage((int)(damage / 2), ownerID);
            else
                playerCol.GetComponent<Player>().RpcTakeDamage((int)(damage), ownerID);
        }
    }

}
