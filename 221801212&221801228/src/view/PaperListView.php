<?php

require_once(dirname(__FILE__) . "\\..\\models\\Paper.php");


class PaperListView
{
    static function renderPaperList($papers)
    {
        foreach ($papers as $paper)
        {
            PaperListView::renderPaper($paper);
        }
    }
    static function renderPaper($paper)
    {
        echo '<div class="panel panel-primary">';
        echo '<div class="panel-heading"';
        echo '<h3 class="panel-title">';
        echo '<a href="'.$paper->link.'" class = "panel-title">'.$paper->title.'</a>';
        echo '</h3>';
        echo '</div>';
        echo '<div class="panel-body">';
        echo $paper->abstract;
        echo '</div>';
        echo '<div class="panel-footer">';
        if ($paper->keywords != null)
        {
            foreach ($paper->keywords as $keyword)
            {
                echo '<a href=#=>'.$keyword.'</a> ';
            }
        }
        echo '</div>';
        echo '</div>';
    }
}