The newspaper **FINKI Onion** decided to develop a mobile application for its news.
All news items `NewsItem` consist of a title, a publication date (an object of class `java.util.Date`), and a category.
The category of a news item is an object of the class `Category`, which stores only the name of the category.
Two categories are equal if their names are equal.

There are currently two types of news published in the newspaper:

* Text news (`TextNewsItem`) which additionally store the text of the news,
* Multimedia news (`MediaNewsItem`) which store the URL to the multimedia content (`String`) and the number of views.

All news items are added into the class `FrontPage`, which stores a list of news items and an array of all existing news categories.
For this class, the following methods need to be implemented:

* constructor: `FrontPage(Category[] categories)`
* `void addNewsItem(NewsItem newsItem)` – adds a new news item to the list
* `List<NewsItem> listByCategory(Category category)` – takes a reference to a `Category` object and returns a list of all news from that category
* `List<NewsItem> listByCategoryName(String category)` – takes as argument a `String` representing the category name and returns a list of all news with that category name.
  If a category with that name does not exist in the category array, an exception of type `CategoryNotFoundException` must be thrown, carrying the name of the category that was not found.
* overriding the `toString()` method, which returns a String composed of all news teasers (calling the `getTeaser()` method).

In the news item classes, the method for returning a teaser `getTeaser()` must be implemented as follows:

* `TextNewsItem::getTeaser()` – returns a String composed of the news title, how many minutes ago it was published (integer minutes), and a maximum of 80 characters of the news text, all separated by a new line.
* `MediaNewsItem::getTeaser()` – returns a String composed of the news title, how many minutes ago it was published (integer minutes), the URL of the news, and the number of views, all separated by a new line.
