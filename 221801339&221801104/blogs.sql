/*
SQLyog Ultimate v8.32 
MySQL - 8.0.23 : Database - blogs
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`blogs` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `blogs`;

/*Table structure for table `t_blog` */

DROP TABLE IF EXISTS `t_blog`;

CREATE TABLE `t_blog` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `appreciation` bit(1) NOT NULL,
  `commentabled` bit(1) NOT NULL,
  `content` longtext,
  `create_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `first_picture` varchar(255) DEFAULT NULL,
  `flag` varchar(255) DEFAULT NULL,
  `published` bit(1) NOT NULL,
  `recommend` bit(1) NOT NULL,
  `share_statement` bit(1) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `views` int DEFAULT NULL,
  `type_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK292449gwg5yf7ocdlmswv9w4j` (`type_id`),
  KEY `FK8ky5rrsxh01nkhctmo7d48p82` (`user_id`),
  CONSTRAINT `FK292449gwg5yf7ocdlmswv9w4j` FOREIGN KEY (`type_id`) REFERENCES `t_type` (`id`),
  CONSTRAINT `FK8ky5rrsxh01nkhctmo7d48p82` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `t_blog` */

insert  into `t_blog`(`id`,`appreciation`,`commentabled`,`content`,`create_time`,`description`,`first_picture`,`flag`,`published`,`recommend`,`share_statement`,`title`,`update_time`,`views`,`type_id`,`user_id`) values (3,'','','**Hongyang Li, David Eigen, Samuel Dodge, Matthew Zeiler, Xiaogang Wang; Proceedings of the IEEE/CVF Conference on Computer Vision and Pattern Recognition (CVPR), 2019, pp. 1-10**\r\n\r\n------------\r\n\r\nFew-shot learning is an important area of research. Conceptually, humans are readily able to understand new concepts given just a few examples, while in more pragmatic terms, limited-example training situations are common practice. Recent effective approaches to few-shot learning employ a metric-learning framework to learn a feature similarity comparison between a query (test) example, and the few support (training) examples. However, these approaches treat each support class independently from one another, never looking at the entire task as a whole. Because of this, they are constrained to use a single set of features for all possible test-time tasks, which hinders the ability to distinguish the most relevant dimensions for the task at hand. In this work, we introduce a Category Traversal Module that can be inserted as a plug-and-play module into most metric-learning based few-shot learners. This component traverses across the entire support set at once, identifying task-relevant features based on both intra-class commonality and inter-class uniqueness in the feature space. Incorporating our module improves performance considerably (5%-10% relative) over baseline systems on both miniImageNet and tieredImageNet benchmarks, with overall performance competitive with the most recent state-of-the-art systems.\r\n\r\n------------\r\n\r\n','2021-03-29 15:21:46','少镜头学习是一个重要的研究领域。从概念上讲，只要举几个例子，人类就可以很容易地理解新概念，而从更实际的角度来说，有限的例子训练情况是常见的做法。最近一些有效的少镜头学习方法使用一个度量学习框架来学习查询（测试）示例和少数支持（训练）示例之间的特征相似性比较。','1','','','','\0','Finding Task-Relevant Features for Few-Shot Learning by Category Traversal','2021-03-29 16:01:04',16,1,1),(4,'','','Kai Zhang, Luc Van Gool, Radu Timofte; Proceedings of the IEEE/CVF Conference on Computer Vision and Pattern Recognition (CVPR), 2020, pp. 3217-3226\r\n------------\r\n\r\nLearning-based single image super-resolution (SISR) methods are continuously showing superior effectiveness and efficiency over traditional model-based methods, largely due to the end-to-end training. However, different from model-based methods that can handle the SISR problem with different scale factors, blur kernels and noise levels under a unified MAP (maximum a posteriori) framework, learning-based methods generally lack such flexibility. To address this issue, this paper proposes an end-to-end trainable unfolding network which leverages both learningbased methods and model-based methods. Specifically, by unfolding the MAP inference via a half-quadratic splitting algorithm, a fixed number of iterations consisting of alternately solving a data subproblem and a prior subproblem can be obtained. The two subproblems then can be solved with neural modules, resulting in an end-to-end trainable, iterative network. As a result, the proposed network inherits the flexibility of model-based methods to super-resolve blurry, noisy images for different scale factors via a single model, while maintaining the advantages of learning-based methods. Extensive experiments demonstrate the superiority of the proposed deep unfolding network in terms of flexibility, effectiveness and also generalizability.\r\n','2021-03-29 15:45:49','基于学习的单图像超分辨率（SISR）方法与传统的基于模型的方法相比，在很大程度上得益于端到端的训练。然而，与基于模型的方法在统一的MAP（最大后验概率）框架下处理具有不同尺度因子、模糊核和噪声水平的SISR问题不同，基于学习的方法普遍缺乏这种灵活性。为了解决这个问题，本文提出了一种端到端可训练的展开网络，它同时利用了基于学习的方法和基于模型的方法。具体地说，通过半二次分裂算法展开映射推理，可以获得','2','','','','\0','Deep Unfolding Network for Image Super-Resolution','2021-03-29 16:01:00',1,1,1),(5,'','','**David Novotny, Samuel Albanie, Diane Larlus, Andrea Vedaldi; Proceedings of the European Conference on Computer Vision (ECCV), 2018, pp. 86-102**\r\n\r\n------------\r\n\r\nObject detection and instance segmentation are dominated by region-based methods such as Mask RCNN. However, there is a growing interest in reducing these problems to pixel labeling tasks, as the latter could be more efficient, could be integrated seamlessly in image-to-image network architectures as used in many other tasks, and could be more accurate for objects that are not well approximated by bounding boxes. In this paper we show theoretically and empirically that constructing dense pixel embeddings that can separate object instances cannot be easily achieved using convolutional operators. At the same time, we show that simple modifications, which we call semi-convolutional, have a much better chance of succeeding at this task. We use the latter to show a connection to Hough voting as well as to a variant of the bilateral kernel that is spatially steered by a convolutional network. We demonstrate that these operators can also be used to improve approaches such as Mask RCNN, demonstrating better segmentation of complex biological shapes and PASCAL VOC categories than achievable by Mask RCNN alone.','2021-03-29 15:57:19','在本文中，我们从理论上和经验上证明了用卷积算子很难构造出能够分离对象实例的密集像素嵌入。同时，我们证明了简单的修改，我们称之为半卷积，有更好的机会在这个任务中取得成功。','3','','','','\0','Semi-convolutional Operators for Instance Segmentation','2021-03-29 16:00:55',1,2,1),(6,'','','**Arsha Nagrani, Samuel Albanie, Andrew Zisserman; Proceedings of the European Conference on Computer Vision (ECCV), 2018, pp. 71-88**\r\n\r\n------------\r\n\r\n\r\n\r\nWe propose and investigate an identity sensitive joint embedding of face and voice. Such an embedding enables cross-modal retrieval from voice to face and from face to voice. We make the following four contributions: first, we show that the embedding can be learnt from videos of talking faces, without requiring any identity labels, using a form of cross-modal self-supervision; second, we develop a curriculum learning schedule for hard negative mining targeted to this task, that is essential for learning to proceed successfully; third, we demonstrate and evaluate cross-modal retrieval for identities unseen and unheard during training over a number of scenarios and establish a benchmark for this novel task; finally, we show an application of using the joint embedding for automatically retrieving and labelling characters in TV dramas.','2021-03-29 16:10:48','提出并研究了一种身份敏感的人脸和语音联合嵌入算法。这种嵌入使得从语音到人脸和从人脸到语音的跨模态检索成为可能。','4','','','','\0','Learnable PINs: Cross-Modal Embeddings for Person Identity','2021-03-31 08:42:35',2,2,1),(7,'','','**Andreas Rossler, Davide Cozzolino, Luisa Verdoliva, Christian Riess, Justus Thies, Matthias Niessner; Proceedings of the IEEE/CVF International Conference on Computer Vision (ICCV), 2019, pp. 1-11**\r\n\r\n------------\r\n\r\nThe rapid progress in synthetic image generation and manipulation has now come to a point where it raises significant concerns for the implications towards society. At best, this leads to a loss of trust in digital content, but could potentially cause further harm by spreading false information or fake news. This paper examines the realism of state-of-the-art image manipulations, and how difficult it is to detect them, either automatically or by humans. To standardize the evaluation of detection methods, we propose an automated benchmark for facial manipulation detection. In particular, the benchmark is based on Deep-Fakes, Face2Face, FaceSwap and NeuralTextures as prominent representatives for facial manipulations at random compression level and size. The benchmark is publicly available and contains a hidden test set as well as a database of over 1.8 million manipulated images. This dataset is over an order of magnitude larger than comparable, publicly available, forgery datasets. Based on this data, we performed a thorough analysis of data-driven forgery detectors. We show that the use of additional domain-specific knowledge improves forgery detection to unprecedented accuracy, even in the presence of strong compression, and clearly outperforms human observers.\r\n','2021-03-29 16:18:46','本文探讨了最新图像处理的真实性，以及自动或人工检测图像的难度。为了规范检测方法的评估，我们提出了一个面部操作检测的自动化基准。特别是，该基准是基于深赝品、Face2Face、FaceSwap和NeuralTextures作为随机压缩级别和大小的面部操作的突出代表。','5','','','\0','\0','FaceForensics++: Learning to Detect Manipulated Facial Images','2021-03-29 16:18:46',0,5,1),(8,'','','**Weixin Lu, Guowei Wan, Yao Zhou, Xiangyu Fu, Pengfei Yuan, Shiyu Song; Proceedings of the IEEE/CVF International Conference on Computer Vision (ICCV), 2019, pp. 12-21\r\n**\r\n\r\n------------\r\n\r\nWe present DeepVCP - a novel end-to-end learning-based 3D point cloud registration framework that achieves comparable registration accuracy to prior state-of-the-art geometric methods. Different from other keypoint based methods where a RANSAC procedure is usually needed, we implement the use of various deep neural network structures to establish an end-to-end trainable network. Our keypoint detector is trained through this end-to-end structure and enables the system to avoid the interference of dynamic objects, leverages the help of sufficiently salient features on stationary objects, and as a result, achieves high robustness. Rather than searching the corresponding points among existing points, the key contribution is that we innovatively generate them based on learned matching probabilities among a group of candidates, which can boost the registration accuracy. We comprehensively validate the effectiveness of our approach using both the KITTI dataset and the Apollo-SouthBay dataset. Results demonstrate that our method achieves comparable registration accuracy and runtime efficiency to the state-of-the-art geometry-based methods, but with higher robustness to inaccurate initial poses. Detailed ablation and visualization analysis are included to further illustrate the behavior and insights of our network. The low registration error and high robustness of our method make it attractive to the substantial applications relying on the point cloud registration task.\r\n\r\n','2021-03-29 16:20:56','我们提出了一种新的基于端到端学习的三维点云配准框架DeepVCP，该框架实现了与现有几何方法相当的配准精度。与其他基于关键点的方法（通常需要一个RANSAC过程）不同，我们实现了使用各种深度神经网络结构来建立端到端可训练网络。','6','','','\0','\0','DeepVCP: An End-to-End Deep Neural Network for Point Cloud Registration','2021-03-29 16:20:56',2,5,1);

/*Table structure for table `t_blog_tags` */

DROP TABLE IF EXISTS `t_blog_tags`;

CREATE TABLE `t_blog_tags` (
  `blogs_id` bigint NOT NULL,
  `tags_id` bigint NOT NULL,
  KEY `FK5feau0gb4lq47fdb03uboswm8` (`tags_id`),
  KEY `FKh4pacwjwofrugxa9hpwaxg6mr` (`blogs_id`),
  CONSTRAINT `FK5feau0gb4lq47fdb03uboswm8` FOREIGN KEY (`tags_id`) REFERENCES `t_tag` (`id`),
  CONSTRAINT `FKh4pacwjwofrugxa9hpwaxg6mr` FOREIGN KEY (`blogs_id`) REFERENCES `t_blog` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_blog_tags` */

insert  into `t_blog_tags`(`blogs_id`,`tags_id`) values (5,4),(5,5),(4,3),(4,4),(3,2),(3,3),(7,7),(8,9),(6,5),(6,6);

/*Table structure for table `t_comment` */

DROP TABLE IF EXISTS `t_comment`;

CREATE TABLE `t_comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `admin_comment` bit(1) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `blog_id` bigint DEFAULT NULL,
  `parent_comment_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKke3uogd04j4jx316m1p51e05u` (`blog_id`),
  KEY `FK4jj284r3pb7japogvo6h72q95` (`parent_comment_id`),
  CONSTRAINT `FK4jj284r3pb7japogvo6h72q95` FOREIGN KEY (`parent_comment_id`) REFERENCES `t_comment` (`id`),
  CONSTRAINT `FKke3uogd04j4jx316m1p51e05u` FOREIGN KEY (`blog_id`) REFERENCES `t_blog` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_comment` */

/*Table structure for table `t_tag` */

DROP TABLE IF EXISTS `t_tag`;

CREATE TABLE `t_tag` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `t_tag` */

insert  into `t_tag`(`id`,`name`) values (2,'Task-Relevant'),(3,'Few-Shot Learning'),(4,'Image Super-Resolution'),(5,'Instance Segmentation'),(6,'Cross-Modal Embeddings'),(7,'FaceForensics++'),(9,'DeepVCP');

/*Table structure for table `t_type` */

DROP TABLE IF EXISTS `t_type`;

CREATE TABLE `t_type` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `t_type` */

insert  into `t_type`(`id`,`name`) values (1,'CVPR'),(2,'ECCV'),(5,'ICCV');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type` int DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`avatar`,`create_time`,`email`,`nickname`,`password`,`type`,`update_time`,`username`) values (1,NULL,'2021-10-01 00:00:00','1163174105@qq.com','zmt','202cb962ac59075b964b07152d234b70',1,'2021-10-01 00:00:00','admin');

/* Procedure structure for procedure `pro_insert_blog` */

/*!50003 DROP PROCEDURE IF EXISTS  `pro_insert_blog` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `pro_insert_blog`(appreciation VARCHAR(50),commentabled VARCHAR(50),content VARCHAR(100),create_time VARCHAR(50),description VARCHAR(200)
,first_picture VARCHAR(50),flag VARCHAR(2),published VARCHAR(50),recommend VARCHAR(50),share_statement VARCHAR(50)
,title VARCHAR(200),views VARCHAR(50),type_id int(50),user_id INT(50))
BEGIN
INSERT INTO t_blog(appreciation,commentabled,content,create_time,description,first_picture,flag,published,recommend,share_statement
,title,views,type_id,user_id) VALUES(appreciation,commentabled,content,create_time,description,first_picture,flag,published,recommend,share_statement
,title,views,type_id,user_id); 
END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
