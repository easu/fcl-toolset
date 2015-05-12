
## 
火狐浏览器的的书签管理不支持排序，默认的按时间排序，由于自己的书签比较多，每次新加的书签都在最后，每次要翻到最后才能找到时。

所以希望它的按自己定义的排序功能。可以按添加时间，正序或反序列出。按网站分类排序。书签与书签夹也是零散按创建时间放着的，不能书签夹在一起，书签在一起。

以前换电脑导了一批书签，可能是操作问题，造成了有很多重复一样的书签，一个个找出来删掉又麻烦，所以需要去重。

然后书签其实可以一定程度上反应出自己最近是看了些什么，解决些什么问题。想按时间线上，做一些标签，如这个月收藏的书签，像博客按时间线分类一样。

在火狐浏览器中，找了很多书签管理的插件，都无法解决自己的问题。
##

## 需求


* 可以按不同字段，类别排序。
* 可以删去重复的书签。
* 可以按不同规则来遍历书签，有选择性的修改。
##

##方式
1. 书签管理工具导出备份
2. 分析导出数据的结构
3. 找出规则，编码进行修改。
4. 书签管理工具恢复修改后的备份

### 的确是这样一个逻辑，只有部分的解决了这个问题，才能定义出来个问题。
##
## 基本结构

``` json

{
  "guid": "root________",
  "title": "",
  "index": 0,
  "dateAdded": 1376875321850000,
  "lastModified": 1376899848952000,
  "id": 1,
  "type": "text/x-moz-place-container",
  "root": "placesRoot",
  "children": [
    {
      "guid": "menu________",
      "title": "书签菜单",
      "index": 0,
      "dateAdded": 1376875321850000,
      "lastModified": 1431317642782000,
      "id": 2,
      "type": "text/x-moz-place-container",
      "root": "bookmarksMenuFolder",
      "children": [
       {
          "guid": "rWBlcfdr-NvR",
          "title": "最近使用的书签",
          "index": 0,
          "dateAdded": 1406727180568000,
          "lastModified": 1422872062053000,
          "id": 46,
          "annos": [
            {
              "name": "Places/SmartBookmark",
              "flags": 0,
              "expires": 4,
              "value": "RecentlyBookmarked"
            }
          ],
          "type": "text/x-moz-place",
          "uri": "place:folder=BOOKMARKS_MENU&folder=UNFILED_BOOKMARKS&folder=TOOLBAR&queryType=1&sort=12&maxResults=10&excludeQueries=1"
        },
        {
          "guid": "ir9Od1_sYqcm",
          "title": "最近使用的标签",
          "index": 1,
          "dateAdded": 1406727180584000,
          "lastModified": 1422872062056000,
          "id": 47,
          "annos": [
            {
              "name": "Places/SmartBookmark",
              "flags": 0,
              "expires": 4,
              "value": "RecentTags"
            }
          ],
          "type": "text/x-moz-place",
          "uri": "place:type=6&sort=14&maxResults=10"
        }
      ]
    },
    {
      "guid": "toolbar_____",
      "title": "书签工具栏",
      "index": 1,
      "dateAdded": 1376875321850000,
      "lastModified": 1427037791350000,
      "id": 3,
      "type": "text/x-moz-place-container",
      "root": "toolbarFolder",
      "children": []
    },
    {
      "guid": "unfiled_____",
      "title": "未分类书签",
      "index": 2,
      "dateAdded": 1376875321850000,
      "lastModified": 1423471313196000,
      "id": 5,
      "type": "text/x-moz-place-container",
      "root": "unfiledBookmarksFolder",
      "children": []
    }
  ]
}

```