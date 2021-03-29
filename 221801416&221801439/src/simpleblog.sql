SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

-- 
-- 鏁版嵁搴�: `simpleblog`
-- 
CREATE DATABASE `simpleblog` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `simpleblog`;

-- --------------------------------------------------------

-- 
-- 琛ㄧ殑缁撴瀯 `post`
-- 

CREATE TABLE `post` (
  `id` int(11) NOT NULL auto_increment,
  `post_title` text character set utf8,
  `post_content` text character set utf8,
  `post_date` datetime,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=5 ;

-- 
-- 瀵煎嚭琛ㄤ腑鐨勬暟鎹� `post`
-- 

INSERT INTO `post` (`id`, `post_title`, `post_content`, `post_date`) VALUES 
(1, '浣胯嚦濉炰笂', '鍗曡溅娆查棶杈癸紝灞炲浗杩囧眳寤躲�俓r\n寰佽摤鍑烘眽濉烇紝褰掗泚鍏ヨ儭澶┿�俓r\n澶ф紶瀛ょ儫鐩达紝闀挎渤钀芥棩鍦嗐�俓r\n钀у叧閫㈠�欓獞锛岄兘鎶ゅ湪鐕曠劧銆�', '2009-03-22 15:28:10'),
(2, '闀跨浉鎬�', '灞变竴绋媆r\n姘翠竴绋媆r\n韬悜姒嗗叧閭ｇ晹琛孿r\n澶滄繁鍗冨笎鐏痋r\n銆�銆�\r\n椋庝竴鏇碶r\n闆竴鏇碶r\n鑱掔涔″績姊︿笉鎴怽r\n鏁呭洯鏃犳澹�', '2009-03-22 15:29:20'),
(3, '濡傛ⅵ浠�', '涓囧笎绌瑰簮浜洪唹\r\n鏄熷奖鎽囨憞娆插潬\r\n\r\n褰掓ⅵ闅旂嫾娌砛r\n鍙堣娌冲０鎼呯\r\n\r\n杩樼潯\r\n杩樼潯\r\n瑙ｉ亾閱掓潵鏃犲懗', '2009-03-22 15:29:30'),
(4, '姊︽睙鍗�', '鏄忛甫灏絓r\n灏忕珛鎭ㄥ洜璋乗r\n鎬ラ洩涔嶇炕棣欓榿绲甛r\n杞婚鍚瑰埌鑳嗙摱姊匼r\n蹇冨瓧宸叉垚鐏�', '2009-03-22 15:32:27');
