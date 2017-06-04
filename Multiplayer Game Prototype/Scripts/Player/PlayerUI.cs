using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class PlayerUI : MonoBehaviour {

    [SerializeField]
    private GameObject pauseMenu;
    [SerializeField]
    private Text healthNumber;
    [SerializeField]
    private GameObject scoreboard;
    
    private Player player;

    [Header("WEAPON INFO")]
    [SerializeField]
    private Image weaponImage;
    [SerializeField]
    private Text currentAmmo;
    [SerializeField]
    private Text clipAmount;
    [SerializeField]
    private RectTransform reloadIndicator;
    [SerializeField]
    private bool reloadAnimPlaying;
    private Coroutine reloadRoutine;

    private PlayerShoot playerShoot;

    // Use this for initialization
    void Start () {
        PauseMenu.isOn = false;
    }

	void Update () {
		if(Input.GetKeyDown(KeyCode.Escape))
        {
            TogglePauseMenu();
        }
        if(Input.GetKeyDown(KeyCode.Tab))
        {
            scoreboard.SetActive(true);
        }
        else if(Input.GetKeyUp(KeyCode.Tab))
        {
            scoreboard.SetActive(false);
        }
        if (healthNumber.text != player.getCurrentHealth().ToString())
            setHealthUI();
        if(playerShoot != null)
        {
            if (playerShoot.activeWeapon == null)
                return;
            if (currentAmmo.text != playerShoot.activeWeapon.currentAmmo.ToString())
                currentAmmo.text = playerShoot.activeWeapon.currentAmmo.ToString();
            if (clipAmount.text != playerShoot.activeWeapon.storedAmmo.ToString())
                clipAmount.text = playerShoot.activeWeapon.storedAmmo.ToString();
            if (weaponImage.sprite != playerShoot.activeWeapon.weaponIcon)
                weaponImage.sprite = playerShoot.activeWeapon.weaponIcon;
            if (playerShoot.isReloading && !reloadAnimPlaying)
            {
                reloadRoutine = StartCoroutine(reloadAnimation());
            }
            else if(!playerShoot.isReloading && !reloadAnimPlaying)
                reloadIndicator.localScale = new Vector3(1,1,1);
            else if(!playerShoot.isReloading && reloadAnimPlaying)
            {
                if(reloadRoutine != null)
                    StopCoroutine(reloadRoutine);
                reloadAnimPlaying = false;
                reloadIndicator.localScale = new Vector3(1, 1, 1);
            }


        }
    }

    private IEnumerator reloadAnimation()
    {
        reloadAnimPlaying = true;
        for (int i = 0; i < 10; i++)
        {
            
            yield return new WaitForSeconds(playerShoot.activeWeapon.ReloadTime/10);
            reloadIndicator.localScale = new Vector3(1, reloadIndicator.localScale.y + 10, 1);
        }
        reloadAnimPlaying = false;

    }

    public void SetPlayer(Player _player)
    {
        player = _player;
        playerShoot = player.GetComponent<PlayerShoot>();
    }
    private void setHealthUI()
    {
        healthNumber.text = player.getCurrentHealth().ToString();
    }
    private void TogglePauseMenu()
    {
        pauseMenu.SetActive(!pauseMenu.activeSelf);
        PauseMenu.isOn = pauseMenu.activeSelf;
        rbController Control = GetComponent<rbController>();
        if (Control != null)
            Control.isPaused = PauseMenu.isOn;
    }
}
