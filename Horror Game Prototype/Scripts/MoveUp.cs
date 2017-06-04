using UnityEngine;
using System.Collections;

public class MoveUp : MonoBehaviour {
	// Use this for initialization

	Light lightz; 
	Light projector;
	[HideInInspector]public Color theColor;

	void Start () {
		
		lightz = transform.FindChild("Light").GetComponent<Light>();
		projector = transform.FindChild ("Projector").GetComponent<Light>();
		lightz.color = theColor;
		this.GetComponent<AudioSource> ().pitch = Random.Range (1.2f, 1.8f);
	}
	
	// Update is called once per frame
	void Update () {
		lightz.range = Mathf.Min (lightz.range + 1 * Time.deltaTime * 10, 15);
		lightz.intensity = lightz.intensity - 1f * Time.deltaTime;

		projector.spotAngle = Mathf.Min (projector.spotAngle + 0.3f * Time.deltaTime * 80, 160);
		projector.gameObject.transform.Rotate (Vector3.forward * Time.deltaTime * 100);
		projector.intensity = Mathf.Max(lightz.intensity - 1f * Time.deltaTime,0);

		if (lightz.intensity <= 0)
			Destroy (this.gameObject); //add object pooling later
	
	}
}
