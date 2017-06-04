using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class UnityManager : MonoBehaviour {
    private bool cursorLocked;
	void Update () {
        if (Input.GetKeyUp(KeyCode.L) && !cursorLocked)
        {
            Cursor.lockState = CursorLockMode.Locked;
            cursorLocked = true;
        }
        else if(Input.GetKeyUp(KeyCode.L) && cursorLocked)
        {
            Cursor.lockState = CursorLockMode.None;
            cursorLocked = false;
        }
	}
}
