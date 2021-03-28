<?php

class Paper
{
    public string $title;
    public string $id;
    public string $abstract;
    public string $link;
    public string $forum;
    public array $keywords;
    public int $year;

    public static function InstancePaperFromRow(array $row) : Paper
    {
        $paper = new Paper();
        $paper->id = $row['id'];
        $paper->title = $row['title'];
        $paper->abstract = $row['abstract'];
        $paper->link = $row['link'];
        $paper->year = intval($row['year']);
        $paper->forum = $row['forum'];
        return $paper;
    }
}