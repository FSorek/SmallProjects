using UnityEngine;
using System.Collections;
using UnityEngine.Networking;

public class PlayerShoot : NetworkBehaviour {
    [SerializeField]
    private AudioSource effectsAudioPlayer;
    [SerializeField]
    private Camera playerCamera;

    [SerializeField]
    private LayerMask hitMask;

    [SerializeField]
    private Transform shootPoint;
    private float shootTimer;
    public PlayerWeapon activeWeapon;

    public PlayerWeapon[] weapons;
    [HideInInspector]
    public bool isReloading;
    private Coroutine reloadRoutine = null;

    private void Start()
    {
        Invoke("setDefaults", 0.01f);
    }
    public void Setup()
    {
        setDefaults();
    }

    public void setDefaults()
    {
        activeWeapon = null;
        foreach (PlayerWeapon wpn in weapons)
        {
            wpn.isUnlocked = false;
        }
        shootTimer = 0;
        weapons[0].isUnlocked = true;
        isReloading = false;
        if(reloadRoutine!=null)
            StopCoroutine(reloadRoutine);
        weapons[0].currentAmmo = weapons[0].ammoInClip;
        setActiveWeapon(0);
    }
    void Update () {
        if (PauseMenu.isOn)
            return;
        if (activeWeapon == null)
            setActiveWeapon(0);
        EquipWeapon();
        if (shootTimer > 0)
        {
            shootTimer -= Time.deltaTime;
            return;
        }
        if (Input.GetButton("Fire1"))
        {
            Shoot();
        }
        if (Input.GetKeyDown(KeyCode.R))
            Reload();
        if (activeWeapon != null)
            if (activeWeapon.currentAmmo <= 0)
                Reload();
	}

    [Client]
    private void Shoot()
    {
        if (activeWeapon == null)
            return;

            if (activeWeapon.currentAmmo <= 0)
            {
                Reload();
                return;
            }
            if (isReloading)
                return;
            activeWeapon.currentAmmo--;
            effectsAudioPlayer.Play();
            CmdShoot(playerCamera.transform.position, shootPoint.transform.rotation);
            shootTimer = activeWeapon.shootDelay;
    }

    private void Reload()
    {
        if (activeWeapon.storedAmmo <= 0)
            return;
        if (isReloading)
            return;
        isReloading = true;
        reloadRoutine = StartCoroutine(reloadingProcess());
    }

    private IEnumerator reloadingProcess()
    {
        yield return new WaitForSeconds(activeWeapon.ReloadTime);
        int toReload = activeWeapon.ammoInClip - activeWeapon.currentAmmo;
        if (activeWeapon.storedAmmo >= toReload)
        {
            activeWeapon.currentAmmo += toReload;
            if(!activeWeapon.InfiniteClips)
                activeWeapon.storedAmmo -= toReload;
        } else
        {
            activeWeapon.currentAmmo += activeWeapon.storedAmmo;
            activeWeapon.storedAmmo = 0;
        }

        isReloading = false;
    } 

    public void UnlockAndRefreshWeapon(int wpnIndex, int baseStoredAmmo)
    {
        if(!weapons[wpnIndex].isUnlocked)
        {
            weapons[wpnIndex].isUnlocked = true;
            weapons[wpnIndex].currentAmmo = weapons[wpnIndex].ammoInClip;
            weapons[wpnIndex].storedAmmo = baseStoredAmmo;
        }
        else
        {
            if (baseStoredAmmo + weapons[wpnIndex].storedAmmo <= baseStoredAmmo * 2)
                weapons[wpnIndex].storedAmmo += baseStoredAmmo;
            else
                weapons[wpnIndex].storedAmmo = baseStoredAmmo * 2;
        }
        


    }

    [Client]
    private void EquipWeapon()
    {
        
        if(Input.GetKeyDown(KeyCode.Alpha1))
        {
            setActiveWeapon(0);
        }
        else if (Input.GetKeyDown(KeyCode.Alpha2))
        {
            setActiveWeapon(1);
        }
        else if(Input.GetKeyDown(KeyCode.Alpha3))
        {
            setActiveWeapon(2);
        }
        else if (Input.GetKeyDown(KeyCode.Alpha4))
        {
            setActiveWeapon(3);
        }
    }

    public int getWeaponIndex(string weaponName)
    {
        for (int i = 0; i < weapons.Length; i++)
        {
            if (weapons[i].weaponName == weaponName)
                return i;
        }
        return -1;
    }

    public void setActiveWeapon(int weaponIndex)
    {
        if (weapons.Length < weaponIndex + 1)
            return;
        if (!weapons[weaponIndex].isUnlocked)
            return;
        if (activeWeapon == weapons[weaponIndex])
            return;
        CmdSetActiveWeapon(weaponIndex);
        abortReload();
    }

    private void abortReload()
    {
        if(reloadRoutine!=null)
            StopCoroutine(reloadRoutine);
        reloadRoutine = null;
        isReloading = false;
    }

    [Command]
    private void CmdSetActiveWeapon(int wpnIndex)
    {
        RpcSetActiveWeapon(wpnIndex);
    }

    [ClientRpc]
    private void RpcSetActiveWeapon(int wpnIndex)
    {
        foreach (PlayerWeapon playerWeapon in weapons)
        {
            playerWeapon.gameObject.SetActive(false);
        }
        weapons[wpnIndex].gameObject.SetActive(true);
        activeWeapon = weapons[wpnIndex];
    }


    [Command]
    private void CmdShoot(Vector3 shootPos, Quaternion rotation)
    {
        if (activeWeapon.GetComponent<PlayerWeapon>().projectileModel != null)
        {
            Projectile _projectile = Instantiate(activeWeapon.projectileModel, shootPos, rotation);
            _projectile.Initialize(activeWeapon.damage, gameObject.name, activeWeapon.projectileLifetime);
            _projectile.GetComponent<Rigidbody>().velocity = _projectile.transform.forward * activeWeapon.projectileSpeed * 100;
            NetworkServer.Spawn(_projectile.gameObject);
        }
        else
        {
            ProjectileRaycast _projectile = Instantiate(activeWeapon.projectileRaycast, shootPos, rotation);
            _projectile.Initialize(activeWeapon.damage, gameObject.name, shootPos, playerCamera.transform.forward, Mathf.Infinity, activeWeapon);
            _projectile.CastRaycast();
            NetworkServer.Spawn(_projectile.gameObject);
            
        }
    }
}
