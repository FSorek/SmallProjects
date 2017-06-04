using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;

public class ProjectileRaycast : NetworkBehaviour {

    protected int damage;
    protected float range;
    private Vector3 startPos;
    private Vector3 aimPos;
    [HideInInspector]
    public PlayerWeapon weaponStats;


    private string ownerID;
    public LayerMask targets;

    private RaycastHit hitInfo;


    public void CastRaycast()
    {
        if(Physics.Raycast(startPos, aimPos, out hitInfo, weaponStats.raycastRange, targets))
        {
            if (hitInfo.collider.GetComponent<Player>())
            {
                if(gameObject.name != ownerID)
                    OnHitPlayerSpecialAction(hitInfo.collider, damage, ownerID, hitInfo);
            }
            else
            {
                OnHitNonPlayerSpecialAction(hitInfo.collider, damage, ownerID);
            }
        }
        Destroy(this.gameObject, 2f);
    }

    protected virtual void OnHitPlayerSpecialAction(Collider playerCollider, int damage, string ownerID, RaycastHit _hitInfo)
    {
        PlayerHit(playerCollider.name, damage, ownerID);
    }

    protected virtual void OnHitNonPlayerSpecialAction(Collider objectCollider, int damage, string ownerID)
    {

    }

    public void Initialize(int _damage, string _ownerID, Vector3 _startPos, Vector3 _aimPos, float _range, PlayerWeapon _wpnStats)
    {
        ownerID = _ownerID;
        damage = _damage;
        startPos = _startPos;
        aimPos = _aimPos;
        range = _range;
        weaponStats = _wpnStats;
    }


    protected virtual void PlayerHit(string _ID, int _damage, string _attackerID)
    {
        Player _player = GameManager.GetPlayer(_ID);
        _player.RpcTakeDamage(_damage, _attackerID);
    }
}
