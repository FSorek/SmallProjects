using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[RequireComponent(typeof(Rigidbody))]
public class Projectile : MonoBehaviour {
    private bool Initialized;
    private int damage;
    private float projectileLifetime;
    private string ownerID;

    void afterInit () {
        Invoke("DestroyProjectile", projectileLifetime);
    }

    private void OnTriggerStay(Collider collider)
    {
        if (!Initialized)
            return;
        if (collider.GetComponent<Player>() && collider.name != ownerID)
        {
            OnHitPlayerSpecialAction(collider, damage, ownerID);
            DestroyProjectile();
        }
        else if (collider.name != ownerID)
        {
            OnHitNonPlayerSpecialAction(collider, damage, ownerID);
            DestroyProjectile();
        }
    }

    protected virtual void OnHitPlayerSpecialAction(Collider playerCollider, int damage, string ownerID)
    {
        PlayerHit(playerCollider.name, damage, ownerID);
    }

    protected virtual void OnHitNonPlayerSpecialAction(Collider objectCollider, int damage, string ownerID)
    {
        
    }

    public void Initialize(int _damage, string _ownerID, float _projectileLifetime)
    {
        ownerID = _ownerID;
        damage = _damage;
        projectileLifetime = _projectileLifetime;
        Initialized = true;
        afterInit();
    }

    protected virtual void DestroyProjectile()
    {
        
        foreach (Transform obj in GetComponentsInChildren<Transform>())
        {
            Destroy(obj.gameObject, 2f);
        }
        transform.DetachChildren();
        Destroy(this.gameObject);
    }
    protected virtual void PlayerHit(string _ID, int _damage, string _attackerID)
    {
        Player _player = GameManager.GetPlayer(_ID);
        _player.RpcTakeDamage(_damage, _attackerID);
    }
}
