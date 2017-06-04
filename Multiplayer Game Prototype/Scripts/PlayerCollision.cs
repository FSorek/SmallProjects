using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;

public class PlayerCollision : NetworkBehaviour {
    private Rigidbody rb;
	// Use this for initialization
	void Start () {
        rb = GetComponent<Rigidbody>();
	}

    [Client]
    private void OnCollisionEnter(Collision collision)
    {
        if (collision.collider.GetComponent<Player>())
        {
            Player playerCollision = collision.collider.GetComponent<Player>();
            if (rb.velocity.magnitude >= 25)
            {
                playerCollision.RpcTakeDamage((int)rb.velocity.magnitude, this.gameObject.name);
            }
        }
    }

    void DamageOtherPlayer(string _ID, int _damage, string _attackerID)
    {
        Player _player = GameManager.GetPlayer(_ID);
        _player.RpcTakeDamage(_damage, _attackerID);
    }
}
