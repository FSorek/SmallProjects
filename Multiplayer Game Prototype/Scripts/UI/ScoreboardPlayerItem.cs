using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ScoreboardPlayerItem : MonoBehaviour {

    [SerializeField]
    private Text playerName;
    [SerializeField]
    private Text playerKills;
    [SerializeField]
    private Text playerDeaths;

    public void Setup(string _playerName, int _playerKills, int _playerDeaths)
    {
        playerName.text = _playerName;
        playerKills.text = _playerKills.ToString();
        playerDeaths.text = _playerDeaths.ToString();
    }
}
