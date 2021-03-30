<?php

class Paper
{
    public string $title = "";
    public string $id = "";
    public string $abstract = "";
    public string $link = "";
    public string $forum = "";
    public array $keywords = array();
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

    public static function InstancePaperFromJson($json)
    {
        $paper = new Paper();
        $paper->id = $json['articleId'];
        if (array_key_exists("abstract", $json))
            $paper->abstract = $json['abstract'];
        else
            $paper->abstract = "";
        $paper->title = $json['title'];
        $paper->link = $json['doiLink'];
        $paper->forum = $json['forum'];
        $paper->year = intval($json['publicationYear']);
        return $paper;
    }
}