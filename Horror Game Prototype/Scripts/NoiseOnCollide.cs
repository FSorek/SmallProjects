using UnityEngine;
using System.Collections;

public class NoiseOnCollide : MonoBehaviour {
	[SerializeField]private GameObject light;
	[HideInInspector]public Color endColor;
	void Start()
	{
		GetComponent<MeshRenderer>().material.SetColor("_EmissionColor",Random.ColorHSV (0,1,0.6f,1,0.7f,1));
		endColor = GetComponent<MeshRenderer> ().material.GetColor ("_EmissionColor");

	}
	void OnCollisionEnter()
	{
		GameObject lit = (GameObject)Instantiate(light, transform.position, light.transform.rotation);
		lit.GetComponent<MoveUp> ().theColor = endColor;
		Destroy (this.gameObject); // add pooling later
	}

}
