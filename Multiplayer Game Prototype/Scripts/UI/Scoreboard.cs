using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Scoreboard : MonoBehaviour {
    [SerializeField]
    private GameObject playerScoreboardItem;
    [SerializeField]
    private Transform scoreboardPlayerList;
    private void OnEnable()
    {
        Player[] players = GameManager.getPlayersArray();

        foreach (Player player in players)
        {
            GameObject itemGO = (GameObject)Instantiate(playerScoreboardItem, scoreboardPlayerList);
            ScoreboardPlayerItem item = itemGO.GetComponent<ScoreboardPlayerItem>();
            if(item != null)
            {
                item.Setup(player.nickname, player.Kills, player.Deaths);
            }
        }
    }

    private void OnDisable()
    {
        foreach (Transform child in scoreboardPlayerList)
        {
            Destroy(child.gameObject);
        }
    }
}
