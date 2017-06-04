using UnityEngine;

[System.Serializable]
public class PlayerWeapon : MonoBehaviour {
    [HideInInspector]
    public bool isUnlocked = false;

    public Sprite weaponIcon;

    public string weaponName = "WeaponName";
    public Projectile projectileModel;
    public ProjectileRaycast projectileRaycast;
    public float raycastRange;
    public float projectileSpeed = 15f;
    public float projectileLifetime = 5f;


    public float shootDelay = 1f;
    public int damage = 10;
    public int ammoInClip = 10;
    [HideInInspector]
    public int currentAmmo;
    public int storedAmmo = 5;
    public float ReloadTime = 4f;

    public bool InfiniteClips;
}
