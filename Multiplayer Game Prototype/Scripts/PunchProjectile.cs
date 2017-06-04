using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PunchProjectile : ProjectileRaycast {

    public float launchForce = 50;
    public float upwardsModifier = 5;
    public float knockbackIncrease = 1;
    protected override void OnHitPlayerSpecialAction(Collider playerCollider, int damage, string ownerID, RaycastHit hitInfo)
    {
        playerCollider.GetComponent<Player>().RpcLaunch(launchForce, range, hitInfo.point, upwardsModifier);
        PlayerHit(playerCollider.name, damage, ownerID);
        playerCollider.GetComponent<Player>().knockbackIncrease += knockbackIncrease;
    }
}
