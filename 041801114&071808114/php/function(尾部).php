function loadCustomTemplate($template) {
	global $wp_query;
	if(!file_exists($template))return;
	$wp_query->is_page = true;
	$wp_query->is_single = false;
	$wp_query->is_home = false;
	$wp_query->comments = false;
	// if we have a 404 status
	if ($wp_query->is_404) {
	// set status of 404 to false
		unset($wp_query->query["error"]);
		$wp_query->query_vars["error"]="";
		$wp_query->is_404=false;
	}
	// change the header to 200 OK
	header("HTTP/1.1 200 OK");
	//load our template
	include($template);
	exit;
}
 
function templateRedirect() {
	$basename = basename($_SERVER['REQUEST_URI'], '?' . $_SERVER['QUERY_STRING']);
	loadCustomTemplate(TEMPLATEPATH.'/quickfind/'."$basename.php");
}
  
add_action('template_redirect', 'templateRedirect');

function safe_num_str($str) {
	$arr = explode(',', $str);
	$impl = array();
	foreach($arr as $num) {
		if (is_numeric($num) && $num > 0) {
			array_push($impl, $num);
		}
	}
	array_push($impl, 0);
	return implode(",", $impl);
}

function keyword_search(){

	global $wpdb;
	
	$impl = safe_num_str($_POST['except']);

	if($_POST['kwd'] == '') {
		$myrows = $wpdb->get_results($wpdb->prepare("SELECT id, title FROM cv_posts WHERE id NOT IN(".$impl.") LIMIT %d, 5;", (number_format($_POST['curr_page']) - 1) * 5));
		$leng = $wpdb->get_col("SELECT count(*) AS cnt FROM cv_posts;");
	} else {
		$myrows = $wpdb->get_results($wpdb->prepare( "SELECT id, title FROM cv_posts WHERE id NOT IN(".$impl.") AND id IN (SELECT posts_id FROM cv_keyword WHERE word LIKE '%%%s%%') LIMIT %d, 5;", $_POST['kwd'], (number_format($_POST['curr_page']) - 1) * 5 ));
		$leng = $wpdb->get_col($wpdb->prepare( "SELECT count(*) AS cnt FROM cv_posts WHERE id IN (SELECT posts_id FROM cv_keyword WHERE word LIKE '%%%s%%');", $_POST['kwd'] ))[0];
	}
    echo json_encode(array('leng'=> $leng, 'row'=>$myrows));
    wp_die();
}
add_action( 'wp_ajax_keyword_search', 'keyword_search' );
add_action( 'wp_ajax_nopriv_keyword_search', 'keyword_search' );

function wordcloud_process() {
	global $wpdb;
	
	$impl = safe_num_str($_POST['except']);
	
	$myrows = $wpdb->get_results( "SELECT word, count(*) AS cnt FROM cv_keyword WHERE posts_id NOT IN(".$impl.") GROUP BY word ORDER BY cnt DESC LIMIT 10;" );
    echo json_encode($myrows);
	wp_die();
}
add_action( 'wp_ajax_wordcloud_process', 'wordcloud_process' );
add_action( 'wp_ajax_nopriv_wordcloud_process', 'wordcloud_process' );

function paper_detail() {
	global $wpdb;
	$myrows = $wpdb->get_results($wpdb->prepare( "SELECT * FROM cv_posts WHERE id = %d;", $_POST['paper_id'] ));
    echo json_encode($myrows[0]);
	wp_die();
}
add_action( 'wp_ajax_paper_detail', 'paper_detail' );
add_action( 'wp_ajax_nopriv_paper_detail', 'paper_detail' );

// SELECT meeting, year, count(*) as cnt FROM wordpress.cv_keyword WHERE word = 'Cameras' group by year, meeting;
function keyword_count() {
	global $wpdb;
	
	$impl = safe_num_str($_POST['except']);
	
	$myrows = $wpdb->get_results($wpdb->prepare("SELECT meeting, year, count(*) as cnt FROM cv_keyword WHERE posts_id NOT IN(".$impl.") AND word = %s GROUP BY year, meeting;", $_POST['kwd']));
    echo json_encode($myrows);
	wp_die();
}
add_action( 'wp_ajax_keyword_count', 'keyword_count' );
add_action( 'wp_ajax_nopriv_keyword_count', 'keyword_count' );