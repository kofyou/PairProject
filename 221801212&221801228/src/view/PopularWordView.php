<?php

class PopularWordView
{
    public static function renderWords($popularWords)
    {
        echo '<ul class = "cloud">';
        $i = 10;
        foreach ($popularWords as $word => $count)
        {
            echo '<li><a data-weight="'.$i.'" href = "/?keyword='.urlencode($word).'">'.$word.'</a></li>';
            $i--;
        }
        echo '</ul>';
    }
}