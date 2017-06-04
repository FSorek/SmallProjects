using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class WeaponPickup : MonoBehaviour {
    public PlayerWeapon ContainedWeapon;

    private void OnTriggerStay(Collider col)
    {
        if (!col.GetComponent<Player>())
            return;
        int unlockedWpnIndx = col.GetComponent<PlayerShoot>().getWeaponIndex(ContainedWeapon.weaponName);
        if (unlockedWpnIndx < 0)
            return;

        col.GetComponent<PlayerShoot>().UnlockAndRefreshWeapon(unlockedWpnIndx, ContainedWeapon.storedAmmo);
        Destroy(gameObject);
    }
}
