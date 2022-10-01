# projectmgm

## Database and Table
### Database: project_management
```
CREATE TABLE `projects` (
  `id` varchar(255) NOT NULL,
  `name` varchar(100) NOT NULL,
  `introduction` varchar(500) DEFAULT NULL,
  `owner` varchar(100) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `current_status` tinyint(4) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
```
