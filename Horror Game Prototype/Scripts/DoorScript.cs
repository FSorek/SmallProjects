using UnityEngine;
using System.Collections;

public class DoorScript : MonoBehaviour {
	public bool open;
	public GameObject noisePrefab;
	public float spawn;
	public float spawnFreq;
	Transform openObj;
	Transform closeObj;
	GameObject noise1;
	GameObject noise2;
	GameObject noise3;
	// Use this for initialization
	void Start () {
		openObj = transform.parent.GetChild(0).transform;
		closeObj = transform.parent.GetChild (1).transform;
		noise1 = transform.parent.GetChild(2).GetChild(0).gameObject;

	}

	
	// Update is called once per frame
	void Update () {
		if (spawn <= 0)
			spawn = 0;
		if (spawn != 0)
			spawn -= Time.deltaTime;
		
		if (open) {
			Invoke ("OpenDoor", 0);
		}
		else
			Invoke ("CloseDoor",0);

	}

	void OpenDoor()
	{
		if (transform.position != openObj.position) {
			transform.position = Vector3.MoveTowards (transform.position, openObj.position, 1 * Time.deltaTime);
			if (spawn == 0) {
				GameObject noise = (GameObject)Instantiate (noisePrefab, noise1.transform.position, Quaternion.LookRotation(new Vector3(0,-90,0),Vector3.up));
				noise.GetComponent<MoveUp> ().theColor = Color.white;
				spawn = spawnFreq;
			}
		}
	}

	void CloseDoor()
	{
		if (transform.position != closeObj.position) {
			transform.position = Vector3.MoveTowards (transform.position, closeObj.position, 1 * Time.deltaTime);
			if (spawn == 0) {
				GameObject noise = (GameObject)Instantiate (noisePrefab, noise1.transform.position, Quaternion.LookRotation(new Vector3(0,-90,0),Vector3.up));
				noise.GetComponent<MoveUp> ().theColor = Color.white;
				spawn = spawnFreq;
			}
		}
	}
}
