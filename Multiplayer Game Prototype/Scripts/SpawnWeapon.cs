using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;

public class SpawnWeapon : NetworkBehaviour
{
    [SerializeField]
    private float spawnDelay = 30f;
    [SerializeField]
    private WeaponPickup WeaponToSpawn;
    [SerializeField]
    private float spawnHeight = 1.5f;

    private GameObject activeBox;

    private enum WeaponSpawnerState
    {
        EMPTY,
        SPAWNING,
        READY
    }

    private WeaponSpawnerState weaponSpawnerState;

    private void Update()
    {
        if (!isServer)
            return;
        if(weaponSpawnerState == WeaponSpawnerState.EMPTY)
        {
            weaponSpawnerState = WeaponSpawnerState.SPAWNING;
            StartCoroutine(StartSpawnWeapon());
        }
        if(weaponSpawnerState == WeaponSpawnerState.READY)
        {
            if(activeBox == null)
            {
                weaponSpawnerState = WeaponSpawnerState.EMPTY;
            }
        }
    }

    private IEnumerator StartSpawnWeapon()
    {
        yield return new WaitForSeconds(spawnDelay);
        activeBox = (GameObject)Instantiate(WeaponToSpawn.gameObject, transform.position + new Vector3(0, spawnHeight, 0), Quaternion.identity);
        NetworkServer.Spawn(activeBox);
        weaponSpawnerState = WeaponSpawnerState.READY;
    }

}
