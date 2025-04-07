package com.example.dissertation_app

import com.example.dissertation_app.data.AppContainer
import com.example.dissertation_app.data.DefaultAppContainer
import com.example.dissertation_app.model.BookObjects
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun main() {
        val jsonString = """
{
  "kind": "books#volumes",
  "totalItems": 1000000,
  "items": [
    {
      "kind": "books#volume",
      "id": "Jx1ojwEACAAJ",
      "etag": "ODNztrabGrw",
      "selfLink": "https://www.googleapis.com/books/v1/volumes/Jx1ojwEACAAJ",
      "volumeInfo": {
        "title": "Harry Potter and the Cursed Child - Parts One and Two",
        "subtitle": "The Official Script Book of the Original West End Production Special Rehearsal Edition",
        "authors": [
          "J. K. Rowling",
          "John Tiffany",
          "Jack Thorne"
        ],
        "publisher": "Arthur A. Levine Books",
        "publishedDate": "2016-07-31",
        "description": "The Eighth Story. Nineteen Years Later. Based on an original new story by J.K. Rowling, John Tiffany, and Jack Thorne, a new play by Jack Thorne, \"Harry Potter and the Cursed Child\" is the eighth story in the Harry Potter series and the first official Harry Potter story to be presented on stage. The play will receive its world premiere in London's West End on July 30, 2016. It was always difficult being Harry Potter and it isn't much easier now that he is an overworked employee of the Ministry of Magic, a husband and father of three school-age children. While Harry grapples with a past that refuses to stay where it belongs, his youngest son Albus must struggle with the weight of a family legacy he never wanted. As past and present fuse ominously, both father and son learn the uncomfortable truth: sometimes, darkness comes from unexpected places. This Special Rehearsal Edition will be available to purchase until early 2017, after which a Definitive Edition of the script will go on sale.",
        "industryIdentifiers": [
          {
            "type": "ISBN_10",
            "identifier": "1338099132"
          },
          {
            "type": "ISBN_13",
            "identifier": "9781338099133"
          }
        ],
        "readingModes": {
          "text": false,
          "image": false
        },
        "pageCount": 336,
        "printType": "BOOK",
        "averageRating": 4.5,
        "ratingsCount": 5,
        "maturityRating": "NOT_MATURE",
        "allowAnonLogging": false,
        "contentVersion": "preview-1.0.0",
        "panelizationSummary": {
          "containsEpubBubbles": false,
          "containsImageBubbles": false
        },
        "imageLinks": {
          "smallThumbnail": "http://books.google.com/books/content?id=Jx1ojwEACAAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api",
          "thumbnail": "http://books.google.com/books/content?id=Jx1ojwEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"
        },
        "language": "en",
        "previewLink": "http://books.google.co.uk/books?id=Jx1ojwEACAAJ&dq=harry+potter&hl=&cd=1&source=gbs_api",
        "infoLink": "http://books.google.co.uk/books?id=Jx1ojwEACAAJ&dq=harry+potter&hl=&source=gbs_api",
        "canonicalVolumeLink": "https://books.google.com/books/about/Harry_Potter_and_the_Cursed_Child_Parts.html?hl=&id=Jx1ojwEACAAJ"
      },
      "saleInfo": {
        "country": "GB",
        "saleability": "NOT_FOR_SALE",
        "isEbook": false
      },
      "accessInfo": {
        "country": "GB",
        "viewability": "NO_PAGES",
        "embeddable": false,
        "publicDomain": false,
        "textToSpeechPermission": "ALLOWED",
        "epub": {
          "isAvailable": false
        },
        "pdf": {
          "isAvailable": false
        },
        "webReaderLink": "http://play.google.com/books/reader?id=Jx1ojwEACAAJ&hl=&source=gbs_api",
        "accessViewStatus": "NONE",
        "quoteSharingAllowed": false
      },
      "searchInfo": {
        "textSnippet": "As an overworked employee of the Ministry of Magic, a husband, and a father, Harry Potter struggles with a past that refuses to stay where it belongs while his youngest son, Albus, finds the weight of the family legacy difficult to bear."
      }
    },
    {
      "kind": "books#volume",
      "id": "pRFbEAAAQBAJ",
      "etag": "YYia1c66YC4",
      "selfLink": "https://www.googleapis.com/books/v1/volumes/pRFbEAAAQBAJ",
      "volumeInfo": {
        "title": "Harry Potter - a Magical Year",
        "subtitle": "The Illustrations of Jim Kay",
        "authors": [
          "J. K. Rowling"
        ],
        "publisher": "Bloomsbury Publishing",
        "publishedDate": "2021",
        "description": "A Magical Year takes readers on an unforgettable journey through the seasons at Hogwarts. Jim Kay's incredible illustrations, paired with much loved quotations from J.K. Rowling's Harry Potter novels, bring to life all of the magic, beauty and wonder of the wizarding world. This is an irresistible gift book for anyone who has ever been captivated by the Boy Who Lived. Each day features a favourite anniversary or meaningful memory from the Harry Potter novels. All around, Jim Kay's scenic artwork and decorative painter's marks bring that moment to life in breathtaking detail. His unique interpretation is both captivating and transporting - picture frozen icicles glinting on the snowy towers of Hogwarts, the dancing eyes of Professor Albus Dumbledore, or the infectious hustle and bustle of Diagon Alley. Inside, a selection of his most iconic illustrations are joined by previously unseen pencil sketches and preparatory pieces, offering a unique and fascinating insight into the artist's sketchbook. Jim Kay's dazzling depiction of the wizarding world has been enchanting readers since the publication of the groundbreaking Illustrated Edition of Harry Potter and the Philosopher's Stone in 2015. Now, as he continues to illustrate the stories, both lifelong fans and new readers can explore that world further. The Kate Greenaway Medal winner's bewitching character studies, sweeping landscapes and beautifully observed pencil details reveal the skill and process of an artist working at the peak of his powers. This is the perfect gift to give at birthdays and Christmas, to share at bedtime or to make a friend smile. A Magical Year brings together Harry, Ron, Hermione and a host of other beloved characters in a glorious illustrated compendium that readers everywhere will cherish for years to come.",
        "industryIdentifiers": [
          {
            "type": "ISBN_13",
            "identifier": "9781526640871"
          },
          {
            "type": "ISBN_10",
            "identifier": "1526640872"
          }
        ],
        "readingModes": {
          "text": false,
          "image": true
        },
        "pageCount": 248,
        "printType": "BOOK",
        "categories": [
          "Fiction"
        ],
        "maturityRating": "NOT_MATURE",
        "allowAnonLogging": false,
        "contentVersion": "0.1.2.0.preview.1",
        "panelizationSummary": {
          "containsEpubBubbles": false,
          "containsImageBubbles": false
        },
        "imageLinks": {
          "smallThumbnail": "http://books.google.com/books/content?id=pRFbEAAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api",
          "thumbnail": "http://books.google.com/books/content?id=pRFbEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
        },
        "language": "en",
        "previewLink": "http://books.google.co.uk/books?id=pRFbEAAAQBAJ&printsec=frontcover&dq=harry+potter&hl=&cd=2&source=gbs_api",
        "infoLink": "http://books.google.co.uk/books?id=pRFbEAAAQBAJ&dq=harry+potter&hl=&source=gbs_api",
        "canonicalVolumeLink": "https://books.google.com/books/about/Harry_Potter_a_Magical_Year.html?hl=&id=pRFbEAAAQBAJ"
      },
      "saleInfo": {
        "country": "GB",
        "saleability": "NOT_FOR_SALE",
        "isEbook": false
      },
      "accessInfo": {
        "country": "GB",
        "viewability": "PARTIAL",
        "embeddable": true,
        "publicDomain": false,
        "textToSpeechPermission": "ALLOWED",
        "epub": {
          "isAvailable": false
        },
        "pdf": {
          "isAvailable": true,
          "acsTokenLink": "http://books.google.co.uk/books/download/Harry_Potter_a_Magical_Year-sample-pdf.acsm?id=pRFbEAAAQBAJ&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api"
        },
        "webReaderLink": "http://play.google.com/books/reader?id=pRFbEAAAQBAJ&hl=&source=gbs_api",
        "accessViewStatus": "SAMPLE",
        "quoteSharingAllowed": false
      },
      "searchInfo": {
        "textSnippet": "This is an irresistible gift book for anyone who has ever been captivated by the Boy Who Lived. Each day features a favourite anniversary or meaningful memory from the Harry Potter novels."
      }
    },
    {
      "kind": "books#volume",
      "id": "QMcAtAEACAAJ",
      "etag": "av3m06pSKXY",
      "selfLink": "https://www.googleapis.com/books/v1/volumes/QMcAtAEACAAJ",
      "volumeInfo": {
        "title": "Harry Potter: Creatures",
        "subtitle": "A Paper Scene Book",
        "authors": [
          "Insight Editions"
        ],
        "publisher": "Insight Kids",
        "publishedDate": "2018-10-02",
        "description": "Discover the creatures of the wizarding world in stunning 3D scenes. Revisit the magic of Harry Potter through four intricate, multilayer dioramas that capture beloved moments from the films. From Harry's pulse-pounding battle with the Hungarian Horntail dragon to his encounter with Thestrals in the Forbidden Forest, each pop-up is laser die-cut for precision and gorgeously detailed. Every scene is followed by essential information about the creatures and fun, behind-the-scenes facts from the films. Designed to thrill both seasoned Harry Potter fans and younger children, this volume is a treasured keepsake for the whole family.",
        "industryIdentifiers": [
          {
            "type": "ISBN_10",
            "identifier": "1683834003"
          },
          {
            "type": "ISBN_13",
            "identifier": "9781683834007"
          }
        ],
        "readingModes": {
          "text": false,
          "image": false
        },
        "pageCount": 0,
        "printType": "BOOK",
        "categories": [
          "Juvenile Nonfiction"
        ],
        "maturityRating": "NOT_MATURE",
        "allowAnonLogging": false,
        "contentVersion": "preview-1.0.0",
        "panelizationSummary": {
          "containsEpubBubbles": false,
          "containsImageBubbles": false
        },
        "imageLinks": {
          "smallThumbnail": "http://books.google.com/books/content?id=QMcAtAEACAAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api",
          "thumbnail": "http://books.google.com/books/content?id=QMcAtAEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"
        },
        "language": "en",
        "previewLink": "http://books.google.co.uk/books?id=QMcAtAEACAAJ&dq=harry+potter&hl=&cd=3&source=gbs_api",
        "infoLink": "http://books.google.co.uk/books?id=QMcAtAEACAAJ&dq=harry+potter&hl=&source=gbs_api",
        "canonicalVolumeLink": "https://books.google.com/books/about/Harry_Potter_Creatures.html?hl=&id=QMcAtAEACAAJ"
      },
      "saleInfo": {
        "country": "GB",
        "saleability": "NOT_FOR_SALE",
        "isEbook": false
      },
      "accessInfo": {
        "country": "GB",
        "viewability": "NO_PAGES",
        "embeddable": false,
        "publicDomain": false,
        "textToSpeechPermission": "ALLOWED",
        "epub": {
          "isAvailable": false
        },
        "pdf": {
          "isAvailable": false
        },
        "webReaderLink": "http://play.google.com/books/reader?id=QMcAtAEACAAJ&hl=&source=gbs_api",
        "accessViewStatus": "NONE",
        "quoteSharingAllowed": false
      },
      "searchInfo": {
        "textSnippet": "Designed to thrill both seasoned Harry Potter fans and younger children, this volume is a treasured keepsake for the whole family."
      }
    },
    {
      "kind": "books#volume",
      "id": "968eEAAAQBAJ",
      "etag": "erNM9uQQnkI",
      "selfLink": "https://www.googleapis.com/books/v1/volumes/968eEAAAQBAJ",
      "volumeInfo": {
        "title": "Harry Potter: The Wand Collection (Book)",
        "authors": [
          "Monique Peterson"
        ],
        "publisher": "Insight Editions",
        "publishedDate": "2017-11-14",
        "description": "Discover the wands of your favorite Harry Potter characters. In the Harry Potter films, each wand is as unique as the witch or wizard who wields it. From Hermione Granger’s elegant, vine-wrapped wand to the bone-inlaid wands of the Death Eaters, each was designed and crafted by the filmmakers to reflect its owner’s identity. Harry Potter: The Wand Collection is a visual guide to these magical wands, their makers, and the characters who mastered them. Profiles of each wand feature stunning new photography of the original props, wand statistics, insights from the cast and crew, and other filmmaking secrets from the Warner Bros. archive. This collectible volume is an ideal resource for both wand-wielding veteran fans seeking to learn the history behind these beloved items and a new generation just beginning their journey into the wizarding world.",
        "industryIdentifiers": [
          {
            "type": "ISBN_13",
            "identifier": "9781683831884"
          },
          {
            "type": "ISBN_10",
            "identifier": "1683831888"
          }
        ],
        "readingModes": {
          "text": false,
          "image": false
        },
        "pageCount": 136,
        "printType": "BOOK",
        "categories": [
          "Performing Arts"
        ],
        "maturityRating": "NOT_MATURE",
        "allowAnonLogging": false,
        "contentVersion": "0.1.2.0.preview.0",
        "panelizationSummary": {
          "containsEpubBubbles": false,
          "containsImageBubbles": false
        },
        "imageLinks": {
          "smallThumbnail": "http://books.google.com/books/content?id=968eEAAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api",
          "thumbnail": "http://books.google.com/books/content?id=968eEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
        },
        "language": "en",
        "previewLink": "http://books.google.co.uk/books?id=968eEAAAQBAJ&printsec=frontcover&dq=harry+potter&hl=&cd=4&source=gbs_api",
        "infoLink": "http://books.google.co.uk/books?id=968eEAAAQBAJ&dq=harry+potter&hl=&source=gbs_api",
        "canonicalVolumeLink": "https://books.google.com/books/about/Harry_Potter_The_Wand_Collection_Book.html?hl=&id=968eEAAAQBAJ"
      },
      "saleInfo": {
        "country": "GB",
        "saleability": "NOT_FOR_SALE",
        "isEbook": false
      },
      "accessInfo": {
        "country": "GB",
        "viewability": "PARTIAL",
        "embeddable": true,
        "publicDomain": false,
        "textToSpeechPermission": "ALLOWED_FOR_ACCESSIBILITY",
        "epub": {
          "isAvailable": false
        },
        "pdf": {
          "isAvailable": false
        },
        "webReaderLink": "http://play.google.com/books/reader?id=968eEAAAQBAJ&hl=&source=gbs_api",
        "accessViewStatus": "SAMPLE",
        "quoteSharingAllowed": false
      },
      "searchInfo": {
        "textSnippet": "This collectible volume is an ideal resource for both wand-wielding veteran fans seeking to learn the history behind these beloved items and a new generation just beginning their journey into the wizarding world."
      }
    },
    {
      "kind": "books#volume",
      "id": "WPC1BAAAQBAJ",
      "etag": "BLrUmBhppdk",
      "selfLink": "https://www.googleapis.com/books/v1/volumes/WPC1BAAAQBAJ",
      "volumeInfo": {
        "title": "101 Amazing Harry Potter Facts",
        "authors": [
          "Jack Goldstein",
          "Frankie Taylor"
        ],
        "publisher": "Andrews UK Limited",
        "publishedDate": "2012-10-16",
        "description": "Through JK Rowling's series of Harry Potter books and the eight films, we have been introduced to a fantastic and magical world that I'm sure many of us would like to visit. But what is the story behind what we see and read, and what are some little-known facts about the books, the films, the actors and the characters? This book contains 101 amazing facts which you most likely didn’t know!",
        "industryIdentifiers": [
          {
            "type": "ISBN_13",
            "identifier": "9781782343554"
          },
          {
            "type": "ISBN_10",
            "identifier": "1782343555"
          }
        ],
        "readingModes": {
          "text": false,
          "image": true
        },
        "pageCount": 25,
        "printType": "BOOK",
        "categories": [
          "Reference"
        ],
        "maturityRating": "NOT_MATURE",
        "allowAnonLogging": true,
        "contentVersion": "0.3.1.0.preview.1",
        "panelizationSummary": {
          "containsEpubBubbles": false,
          "containsImageBubbles": false
        },
        "imageLinks": {
          "smallThumbnail": "http://books.google.com/books/content?id=WPC1BAAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api",
          "thumbnail": "http://books.google.com/books/content?id=WPC1BAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
        },
        "language": "en",
        "previewLink": "http://books.google.co.uk/books?id=WPC1BAAAQBAJ&printsec=frontcover&dq=harry+potter&hl=&cd=5&source=gbs_api",
        "infoLink": "https://play.google.com/store/books/details?id=WPC1BAAAQBAJ&source=gbs_api",
        "canonicalVolumeLink": "https://play.google.com/store/books/details?id=WPC1BAAAQBAJ"
      },
      "saleInfo": {
        "country": "GB",
        "saleability": "FOR_SALE",
        "isEbook": true,
        "listPrice": {
          "amount": 2.99,
          "currencyCode": "GBP"
        },
        "retailPrice": {
          "amount": 2.99,
          "currencyCode": "GBP"
        },
        "buyLink": "https://play.google.com/store/books/details?id=WPC1BAAAQBAJ&rdid=book-WPC1BAAAQBAJ&rdot=1&source=gbs_api",
        "offers": [
          {
            "finskyOfferType": 1,
            "listPrice": {
              "amountInMicros": 2990000,
              "currencyCode": "GBP"
            },
            "retailPrice": {
              "amountInMicros": 2990000,
              "currencyCode": "GBP"
            },
            "giftable": true
          }
        ]
      },
      "accessInfo": {
        "country": "GB",
        "viewability": "PARTIAL",
        "embeddable": true,
        "publicDomain": false,
        "textToSpeechPermission": "ALLOWED",
        "epub": {
          "isAvailable": false
        },
        "pdf": {
          "isAvailable": true,
          "acsTokenLink": "http://books.google.co.uk/books/download/101_Amazing_Harry_Potter_Facts-sample-pdf.acsm?id=WPC1BAAAQBAJ&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api"
        },
        "webReaderLink": "http://play.google.com/books/reader?id=WPC1BAAAQBAJ&hl=&source=gbs_api",
        "accessViewStatus": "SAMPLE",
        "quoteSharingAllowed": false
      },
      "searchInfo": {
        "textSnippet": "But what is the story behind what we see and read, and what are some little-known facts about the books, the films, the actors and the characters? This book contains 101 amazing facts which you most likely didn’t know!"
      }
    },
    {
      "kind": "books#volume",
      "id": "dQRbEAAAQBAJ",
      "etag": "9AoWnGMV1xU",
      "selfLink": "https://www.googleapis.com/books/v1/volumes/dQRbEAAAQBAJ",
      "volumeInfo": {
        "title": "Harry Potter",
        "subtitle": "A History of Magic",
        "authors": [
          "British Library"
        ],
        "publisher": "Bloomsbury Publishing",
        "publishedDate": "2017",
        "description": "Harry Potter: A History of Magicis the official book of the exhibition, a once-in-a-lifetime collaboration between Bloomsbury, J.K. Rowling and the brilliant curators of the British Library. It promises to take readers on a fascinating journey through the subjects studied at Hogwarts School of Witchcraft and Wizardry - from Alchemy and Potions classes through to Herbology and Care of Magical Creatures. Each chapter showcases a treasure trove of artefacts from the British Library and other collections around the world, beside exclusive manuscripts, sketches and illustrations from the Harry Potter archive. There's also a specially commissioned essay for each subject area by an expert, writer or cultural commentator, inspired by the contents of the exhibition - absorbing, insightful and unexpected contributions from Steve Backshall, the Reverend Richard Coles, Owen Davies, Julia Eccleshare,Roger Highfield, Steve Kloves, Lucy Mangan, Anna Pavord and Tim Peake, who offer a personal perspective on their magical theme. Readers will be able to pore over ancient spell books, amazing illuminated scrolls that reveal the secret of the Elixir of Life, vials of dragon's blood, mandrake roots, painted centaurs and a genuine witch's broomstick, in a book that shows J.K. Rowling's magical inventions alongside their cultural and historical forebears. This is the ultimate gift for Harry Potter fans, curious minds, big imaginations, bibliophiles and readers around the world who missed out on the chance to see the exhibition in person.",
        "industryIdentifiers": [
          {
            "type": "ISBN_13",
            "identifier": "9781408890769"
          },
          {
            "type": "ISBN_10",
            "identifier": "1408890763"
          }
        ],
        "readingModes": {
          "text": false,
          "image": true
        },
        "pageCount": 264,
        "printType": "BOOK",
        "categories": [
          "Art"
        ],
        "maturityRating": "NOT_MATURE",
        "allowAnonLogging": false,
        "contentVersion": "0.1.2.0.preview.1",
        "panelizationSummary": {
          "containsEpubBubbles": false,
          "containsImageBubbles": false
        },
        "imageLinks": {
          "smallThumbnail": "http://books.google.com/books/content?id=dQRbEAAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api",
          "thumbnail": "http://books.google.com/books/content?id=dQRbEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
        },
        "language": "en",
        "previewLink": "http://books.google.co.uk/books?id=dQRbEAAAQBAJ&printsec=frontcover&dq=harry+potter&hl=&cd=6&source=gbs_api",
        "infoLink": "http://books.google.co.uk/books?id=dQRbEAAAQBAJ&dq=harry+potter&hl=&source=gbs_api",
        "canonicalVolumeLink": "https://books.google.com/books/about/Harry_Potter.html?hl=&id=dQRbEAAAQBAJ"
      },
      "saleInfo": {
        "country": "GB",
        "saleability": "NOT_FOR_SALE",
        "isEbook": false
      },
      "accessInfo": {
        "country": "GB",
        "viewability": "PARTIAL",
        "embeddable": true,
        "publicDomain": false,
        "textToSpeechPermission": "ALLOWED",
        "epub": {
          "isAvailable": false
        },
        "pdf": {
          "isAvailable": true,
          "acsTokenLink": "http://books.google.co.uk/books/download/Harry_Potter-sample-pdf.acsm?id=dQRbEAAAQBAJ&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api"
        },
        "webReaderLink": "http://play.google.com/books/reader?id=dQRbEAAAQBAJ&hl=&source=gbs_api",
        "accessViewStatus": "SAMPLE",
        "quoteSharingAllowed": false
      },
      "searchInfo": {
        "textSnippet": "This is the ultimate gift for Harry Potter fans, curious minds, big imaginations, bibliophiles and readers around the world who missed out on the chance to see the exhibition in person."
      }
    },
    {
      "kind": "books#volume",
      "id": "oIc7ugEACAAJ",
      "etag": "xlmZRGqWf6g",
      "selfLink": "https://www.googleapis.com/books/v1/volumes/oIc7ugEACAAJ",
      "volumeInfo": {
        "title": "Harry Potter",
        "subtitle": "A History of Magic",
        "authors": [
          "British Library"
        ],
        "publisher": "Bloomsbury Children's Books",
        "publishedDate": "2018",
        "description": "Harry Potter: A History of Magic is the official book of the record-breaking British Library exhibition, a once-in-a-lifetime collaboration between Bloomsbury, J.K. Rowling and a team of brilliant curators. As the spectacular show takes up residence at the New York Historical Society from October 2018, this gorgeous book - available in paperback for the first time - takes readers on a fascinating journey through the subjects studied at Hogwarts School of Witchcraft and Wizardry, from Astronomy and Potions through to Herbology and Care of Magical Creatures. Each chapter showcases a treasure trove of artefacts from the British Library and other collections around the world, beside exclusive manuscripts, sketches and illustrations from the Harry Potter archive. There's also a specially commissioned essay for each subject area by an expert, writer or cultural commentator, inspired by the contents of the exhibition - absorbing, insightful and unexpected contributions from Steve Backshall, the Reverend Richard Coles, Owen Davies, Julia Eccleshare, Roger Highfield, Steve Kloves, Lucy Mangan, Anna Pavord and Tim Peake, who offer a personal perspective on their magical theme. Readers will be able to pore over ancient spell books, amazing illuminated scrolls that reveal the secret of the Elixir of Life, vials of dragon's blood, mandrake roots, painted centaurs and a genuine witch's broomstick, in a book that shows J.K. Rowling's magical inventions alongside their cultural and historical forebears. This is the ultimate gift for Harry Potter fans, curious minds, big imaginations, bibliophiles and readers around the world who missed out on the chance to see the exhibition in person.",
        "industryIdentifiers": [
          {
            "type": "ISBN_13",
            "identifier": "9781526607072"
          },
          {
            "type": "ISBN_10",
            "identifier": "1526607077"
          }
        ],
        "readingModes": {
          "text": false,
          "image": false
        },
        "pageCount": 264,
        "printType": "BOOK",
        "categories": [
          "Art"
        ],
        "maturityRating": "NOT_MATURE",
        "allowAnonLogging": false,
        "contentVersion": "1.2.3.0.preview.0",
        "panelizationSummary": {
          "containsEpubBubbles": false,
          "containsImageBubbles": false
        },
        "imageLinks": {
          "smallThumbnail": "http://books.google.com/books/content?id=oIc7ugEACAAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api",
          "thumbnail": "http://books.google.com/books/content?id=oIc7ugEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"
        },
        "language": "en",
        "previewLink": "http://books.google.co.uk/books?id=oIc7ugEACAAJ&dq=harry+potter&hl=&cd=7&source=gbs_api",
        "infoLink": "http://books.google.co.uk/books?id=oIc7ugEACAAJ&dq=harry+potter&hl=&source=gbs_api",
        "canonicalVolumeLink": "https://books.google.com/books/about/Harry_Potter.html?hl=&id=oIc7ugEACAAJ"
      },
      "saleInfo": {
        "country": "GB",
        "saleability": "NOT_FOR_SALE",
        "isEbook": false
      },
      "accessInfo": {
        "country": "GB",
        "viewability": "NO_PAGES",
        "embeddable": false,
        "publicDomain": false,
        "textToSpeechPermission": "ALLOWED",
        "epub": {
          "isAvailable": false
        },
        "pdf": {
          "isAvailable": true
        },
        "webReaderLink": "http://play.google.com/books/reader?id=oIc7ugEACAAJ&hl=&source=gbs_api",
        "accessViewStatus": "NONE",
        "quoteSharingAllowed": false
      },
      "searchInfo": {
        "textSnippet": "Harry Potter: A History of Magic is the official book of the record-breaking British Library exhibition, a once-in-a-lifetime collaboration between Bloomsbury, J.K. Rowling and a team of brilliant curators."
      }
    },
    {
      "kind": "books#volume",
      "id": "BJvHBAAAQBAJ",
      "etag": "dJ2ooY+IqfM",
      "selfLink": "https://www.googleapis.com/books/v1/volumes/BJvHBAAAQBAJ",
      "volumeInfo": {
        "title": "Harry Potter and the Deathly Hallows",
        "authors": [
          "J. K. Rowling"
        ],
        "publisher": "Bloomsbury Publishing",
        "publishedDate": "2014-08-27",
        "description": "Celebrate 20 years of Harry Potter magic! As he climbs into the sidecar of Hagrid's motorbike and takes to the skies, leaving Privet Drive for the last time, Harry Potter knows that Lord Voldemort and the Death Eaters are not far behind. The protective charm that has kept Harry safe until now is now broken, but he cannot keep hiding. The Dark Lord is breathing fear into everything Harry loves, and to stop him Harry will have to find and destroy the remaining Horcruxes. The final battle must begin - Harry must stand and face his enemy.These new editions of the classic and internationally bestselling, multi-award-winning series feature instantly pick-up-able new jackets by Jonny Duddle, with huge child appeal, to bring Harry Potter to the next generation of readers. It's time to PASS THE MAGIC ON ...",
        "industryIdentifiers": [
          {
            "type": "ISBN_13",
            "identifier": "9781408855959"
          },
          {
            "type": "ISBN_10",
            "identifier": "140885595X"
          }
        ],
        "readingModes": {
          "text": false,
          "image": true
        },
        "pageCount": 638,
        "printType": "BOOK",
        "categories": [
          "Fiction"
        ],
        "maturityRating": "NOT_MATURE",
        "allowAnonLogging": false,
        "contentVersion": "0.3.3.0.preview.1",
        "panelizationSummary": {
          "containsEpubBubbles": false,
          "containsImageBubbles": false
        },
        "imageLinks": {
          "smallThumbnail": "http://books.google.com/books/content?id=BJvHBAAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api",
          "thumbnail": "http://books.google.com/books/content?id=BJvHBAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
        },
        "language": "en",
        "previewLink": "http://books.google.co.uk/books?id=BJvHBAAAQBAJ&printsec=frontcover&dq=harry+potter&hl=&cd=8&source=gbs_api",
        "infoLink": "http://books.google.co.uk/books?id=BJvHBAAAQBAJ&dq=harry+potter&hl=&source=gbs_api",
        "canonicalVolumeLink": "https://books.google.com/books/about/Harry_Potter_and_the_Deathly_Hallows.html?hl=&id=BJvHBAAAQBAJ"
      },
      "saleInfo": {
        "country": "GB",
        "saleability": "NOT_FOR_SALE",
        "isEbook": false
      },
      "accessInfo": {
        "country": "GB",
        "viewability": "PARTIAL",
        "embeddable": true,
        "publicDomain": false,
        "textToSpeechPermission": "ALLOWED",
        "epub": {
          "isAvailable": false
        },
        "pdf": {
          "isAvailable": true,
          "acsTokenLink": "http://books.google.co.uk/books/download/Harry_Potter_and_the_Deathly_Hallows-sample-pdf.acsm?id=BJvHBAAAQBAJ&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api"
        },
        "webReaderLink": "http://play.google.com/books/reader?id=BJvHBAAAQBAJ&hl=&source=gbs_api",
        "accessViewStatus": "SAMPLE",
        "quoteSharingAllowed": false
      },
      "searchInfo": {
        "textSnippet": "The final battle must begin - Harry must stand and face his enemy.These new editions of the classic and internationally bestselling, multi-award-winning series feature instantly pick-up-able new jackets by Jonny Duddle, with huge child ..."
      }
    },
    {
      "kind": "books#volume",
      "id": "FmwwDQAAQBAJ",
      "etag": "ECGb6iQrHac",
      "selfLink": "https://www.googleapis.com/books/v1/volumes/FmwwDQAAQBAJ",
      "volumeInfo": {
        "title": "Harry Potter and the Order of the Phoenix",
        "authors": [
          "J. K. Rowling"
        ],
        "publisher": "Bloomsbury Publishing",
        "publishedDate": "2014-08-27",
        "description": "Fantasy. Da Harry Potter vender tilbage til Hogwarts er meget ændret. Man tror, at han lyver angående Voldemort, og ministeriet sender en repræsentant til skolen, der snart er delt i to fjendtlige lejre",
        "industryIdentifiers": [
          {
            "type": "ISBN_13",
            "identifier": "9781408855935"
          },
          {
            "type": "ISBN_10",
            "identifier": "1408855933"
          }
        ],
        "readingModes": {
          "text": false,
          "image": true
        },
        "pageCount": 815,
        "printType": "BOOK",
        "categories": [
          "Fiction"
        ],
        "averageRating": 3,
        "ratingsCount": 1,
        "maturityRating": "NOT_MATURE",
        "allowAnonLogging": false,
        "contentVersion": "0.1.2.0.preview.1",
        "panelizationSummary": {
          "containsEpubBubbles": false,
          "containsImageBubbles": false
        },
        "imageLinks": {
          "smallThumbnail": "http://books.google.com/books/content?id=FmwwDQAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api",
          "thumbnail": "http://books.google.com/books/content?id=FmwwDQAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
        },
        "language": "en",
        "previewLink": "http://books.google.co.uk/books?id=FmwwDQAAQBAJ&printsec=frontcover&dq=harry+potter&hl=&cd=9&source=gbs_api",
        "infoLink": "http://books.google.co.uk/books?id=FmwwDQAAQBAJ&dq=harry+potter&hl=&source=gbs_api",
        "canonicalVolumeLink": "https://books.google.com/books/about/Harry_Potter_and_the_Order_of_the_Phoeni.html?hl=&id=FmwwDQAAQBAJ"
      },
      "saleInfo": {
        "country": "GB",
        "saleability": "NOT_FOR_SALE",
        "isEbook": false
      },
      "accessInfo": {
        "country": "GB",
        "viewability": "PARTIAL",
        "embeddable": true,
        "publicDomain": false,
        "textToSpeechPermission": "ALLOWED",
        "epub": {
          "isAvailable": false
        },
        "pdf": {
          "isAvailable": true,
          "acsTokenLink": "http://books.google.co.uk/books/download/Harry_Potter_and_the_Order_of_the_Phoeni-sample-pdf.acsm?id=FmwwDQAAQBAJ&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api"
        },
        "webReaderLink": "http://play.google.com/books/reader?id=FmwwDQAAQBAJ&hl=&source=gbs_api",
        "accessViewStatus": "SAMPLE",
        "quoteSharingAllowed": false
      },
      "searchInfo": {
        "textSnippet": "Fantasy. Da Harry Potter vender tilbage til Hogwarts er meget ændret. Man tror, at han lyver angående Voldemort, og ministeriet sender en repræsentant til skolen, der snart er delt i to fjendtlige lejre"
      }
    },
    {
      "kind": "books#volume",
      "id": "7yyUswEACAAJ",
      "etag": "VFdn9SjLM+0",
      "selfLink": "https://www.googleapis.com/books/v1/volumes/7yyUswEACAAJ",
      "volumeInfo": {
        "title": "Harry Potter - The Illustrated Collection",
        "authors": [
          "J. K. Rowling"
        ],
        "publisher": "Bloomsbury Publishing",
        "publishedDate": "2017-11-16",
        "industryIdentifiers": [
          {
            "type": "ISBN_10",
            "identifier": "1408897318"
          },
          {
            "type": "ISBN_13",
            "identifier": "9781408897317"
          }
        ],
        "readingModes": {
          "text": false,
          "image": false
        },
        "printType": "BOOK",
        "averageRating": 5,
        "ratingsCount": 1,
        "maturityRating": "NOT_MATURE",
        "allowAnonLogging": false,
        "contentVersion": "preview-1.0.0",
        "panelizationSummary": {
          "containsEpubBubbles": false,
          "containsImageBubbles": false
        },
        "imageLinks": {
          "smallThumbnail": "http://books.google.com/books/content?id=7yyUswEACAAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api",
          "thumbnail": "http://books.google.com/books/content?id=7yyUswEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"
        },
        "language": "en",
        "previewLink": "http://books.google.co.uk/books?id=7yyUswEACAAJ&dq=harry+potter&hl=&cd=10&source=gbs_api",
        "infoLink": "http://books.google.co.uk/books?id=7yyUswEACAAJ&dq=harry+potter&hl=&source=gbs_api",
        "canonicalVolumeLink": "https://books.google.com/books/about/Harry_Potter_The_Illustrated_Collection.html?hl=&id=7yyUswEACAAJ"
      },
      "saleInfo": {
        "country": "GB",
        "saleability": "NOT_FOR_SALE",
        "isEbook": false
      },
      "accessInfo": {
        "country": "GB",
        "viewability": "NO_PAGES",
        "embeddable": false,
        "publicDomain": false,
        "textToSpeechPermission": "ALLOWED",
        "epub": {
          "isAvailable": false
        },
        "pdf": {
          "isAvailable": false
        },
        "webReaderLink": "http://play.google.com/books/reader?id=7yyUswEACAAJ&hl=&source=gbs_api",
        "accessViewStatus": "NONE",
        "quoteSharingAllowed": false
      },
      "searchInfo": {
        "textSnippet": "This illustrated collection is the beginning of an amazing magical adventure and the perfect gift for Harry Potter fans of any age."
      }
    }
  ]
}

    """

        try {
            val bookResponse = Json.decodeFromString<BookObjects>(jsonString)
            println("Deserialized successfully: books found")

        } catch (e: Exception) {
            println("Deserialization error: ${e.message}")
            e.printStackTrace()
        }
    }

    @Test
    fun testBookRepository() = runBlocking {

        var container: AppContainer = DefaultAppContainer()

        var books = container.bookRepository.getBooks("Harry+Potter").items

        if (books != null) {
            println("empty list")
        } else {
            println("not empty")
        }
    }
}