using UnityEngine;
using UnityEngine.Networking;
using System.Collections;
public class Player : NetworkBehaviour {
    [SyncVar]
    public string nickname = "NoNameKNUB";
    [SyncVar]
    private bool _isDead = false;

    [SyncVar]
    public int Kills = 0;
    [SyncVar]
    public int Deaths = 0;
    public bool isDead
    {
        get { return _isDead; }
        protected set { _isDead = value; }
    }
    [SerializeField]
    private int maxHealth = 100;

    [SyncVar]
    private int currentHealth;

    [SerializeField]
    private Behaviour[] disableOnDeath;
    private bool[] wasEnabled;

    [SerializeField]
    private GameObject DeathEffect;
    [SerializeField]
    private float YPosDeathLimit = -10f;
    private bool justSpawned = false;
    [SyncVar]
    public float knockbackIncrease = 0;

    public void Setup()
    {
        wasEnabled = new bool[disableOnDeath.Length];
        for (int i = 0; i < wasEnabled.Length; i++)
        {
            wasEnabled[i] = disableOnDeath[i].enabled;
        }
        SetDefaults();
        if (isLocalPlayer)
            InvokeRepeating("CheckHeight", 1f, 4f);
        justSpawned = false;
        GetComponent<PlayerShoot>().Setup();
    }
    private void Update()
    {
        if (!isLocalPlayer)
            return;
        if (Input.GetKeyDown(KeyCode.K))
        {
            CmdTakeDamage(10, this.gameObject.name);
        }
        CheckHeight();
    }

    private void CheckHeight()
    {
        if (transform.position.y < YPosDeathLimit && !justSpawned)
            CmdTakeDamage(100, this.gameObject.name);
    }
    public int getCurrentHealth()
    {
        return currentHealth;
    }

    [Command]
    public void CmdTakeDamage(int _damage, string _attackerID)
    {
        RpcTakeDamage(_damage, _attackerID);
    }

    [ClientRpc]
    public void RpcTakeDamage(int _damage, string _attackerID)
    {
        if (isDead)
            return;
        currentHealth -= _damage;
        Debug.Log("Health: " + currentHealth);
        if (currentHealth <= 0)
            Death(_attackerID);
    }

    [ClientRpc]
    public void RpcLaunch(float force, float range, Vector3 position, float upwardsModifier)
    {
        GetComponent<Rigidbody>().drag = 0;
        GetComponent<Rigidbody>().AddExplosionForce(force * (1 + knockbackIncrease), position, range, upwardsModifier);
        GetComponent<rbController>().m_Jump = true;
    }

    [ClientRpc]
    public void RpcAddForce(Vector3 forceDirection, float upwardsModifier, float forcePower)
    {
        GetComponent<Rigidbody>().drag = 0;
        GetComponent<Rigidbody>().AddForce(forceDirection * forcePower + new Vector3(0f, upwardsModifier, 0f), ForceMode.Impulse);
        GetComponent<rbController>().m_Jump = true;
    }

    private void Death(string _attackerID)
    {
        GetComponent<Rigidbody>().useGravity = false;
        GetComponent<Rigidbody>().isKinematic = true;
        GameObject DeathEffGO = Instantiate(DeathEffect, transform.position, Quaternion.identity);
        Destroy(DeathEffGO, 2f);
        isDead = true;
        for (int i = 0; i < disableOnDeath.Length; i++)
        {
            disableOnDeath[i].enabled = false;
        }
        Collider _col = GetComponent<Collider>();
        if (_col != null)
            _col.enabled = false;
        StartCoroutine(Respawn());
        Player attacker = GameManager.GetPlayer(_attackerID);
        if(attacker != this)
            attacker.Kills++;
        Deaths++;
        justSpawned = true;
        GameManager.Instance.onPlayerKilledCallback.Invoke(nickname, attacker.nickname);
    }

    private IEnumerator Respawn()
    {
        yield return new WaitForSeconds(GameManager.Instance.matchSettings.respawnTime);
        SetDefaults();
        Transform _spawnPoint = NetworkManager.singleton.GetStartPosition();
        transform.position = _spawnPoint.position;
        transform.rotation = _spawnPoint.rotation;
        
    }

    public void SetDefaults()
    {
        Invoke("endSpawnProtection", 1.5f);
        isDead = false;
        currentHealth = maxHealth;
        for (int i = 0; i < disableOnDeath.Length; i++)
        {
            disableOnDeath[i].enabled = wasEnabled[i];
        }

        Collider _col = GetComponent<Collider>();
        if (_col != null)
            _col.enabled = true;
        GetComponent<Rigidbody>().useGravity = true;
        GetComponent<Rigidbody>().isKinematic = false;
        GetComponent<PlayerShoot>().setDefaults();
        knockbackIncrease = 0;
    }

    public void endSpawnProtection()
    {
        justSpawned = false;
    }
}
