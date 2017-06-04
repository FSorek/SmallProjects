using UnityEngine;
using System.Collections;

public class Carry : MonoBehaviour {
	public bool isCarried;
	bool playing;
	bool lockbool;
	// Use this for initialization
	void Start () {
		playing = false;
		lockbool = false;
	}
	
	// Update is called once per frame
	void Update ()
	{
		if (isCarried) {
			if (!playing) {
				GetComponent<AudioSource> ().Play ();
				playing = true;
			}
		} else {
			if (playing) {
				playing = false;
			}
		}
		if (!isCarried) {
			if (!lockbool) {
				GetComponent<AudioSource> ().Stop ();
				lockbool = true;
			}
		} else {
			if (lockbool) {
				lockbool = false;
			}
		}
	}
}