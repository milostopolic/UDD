import { Component, OnInit } from '@angular/core';
import { WorkService } from '../services/work.service';
import { SearchService } from '../services/search.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  odabranKriterijum = 0;
  kriterijum = "";
  kaoFraza = false;
  naucneOblasti = [];
  odabranaOblast = 0;
  prikaziRezultate: boolean = false;
  rezultati = [];
  odabraneOblasti = [];
  url;

  magazineName = "";
  magazineCheck = true;
  title = "";
  titleCheck = true;
  authors = "";
  authorsCheck = true;
  keyTerms = "";
  keyTermsCheck = true;
  content = "";
  contentCheck = true;

  constructor(private workService: WorkService, private searchService: SearchService) {
    this.workService.getujNaucneOblasti().subscribe(data => {
      this.naucneOblasti = data;
      console.log(this.naucneOblasti);
    });
  }

  ngOnInit() {
  }

  onSubmit() {
    console.log(this.kriterijum);
    console.log(this.kaoFraza);
    console.log(this.odabranKriterijum);
    var fraza: number = 0;
    if (this.kaoFraza) {
      fraza = 1;
    }

    if (this.odabranKriterijum == 1) {
      this.searchService.searchByMagazineName(this.kriterijum, fraza).subscribe(data => {
        console.log(data);
        this.rezultati = data;
        this.prikaziRezultate = true;
      });
    }
    if (this.odabranKriterijum == 2) {
      this.searchService.searchByTitle(this.kriterijum, fraza).subscribe(data => {
        console.log(data);
        this.rezultati = data;
        this.prikaziRezultate = true;
      });
    }
    if (this.odabranKriterijum == 3) {
      this.searchService.searchByAuthors(this.kriterijum, fraza).subscribe(data => {
        console.log(data);
        this.rezultati = data;
        this.prikaziRezultate = true;
      });
    }
    if (this.odabranKriterijum == 4) {
      this.searchService.searchByKeyTerms(this.kriterijum, fraza).subscribe(data => {
        console.log(data);
        this.rezultati = data;
        this.prikaziRezultate = true;
      });
    }
    if (this.odabranKriterijum == 5) {
      this.searchService.searchByWorkContent(this.kriterijum, fraza).subscribe(data => {
        console.log(data);
        this.rezultati = data;
        this.prikaziRezultate = true;
      });
    }
    if (this.odabranKriterijum == 6) {
      console.log(this.odabraneOblasti);
      var temp = "";
      for (let i = 0; i < this.odabraneOblasti.length; i++) {
        temp = temp + this.odabraneOblasti[i] + '-';
      }
      if (temp == "") {
        alert("Oaberite naucne oblasti");
        return;
      }
      temp = temp.substring(0, temp.length - 1);

      this.searchService.searchByScienceAreas(temp).subscribe(data => {
        console.log(data);
        this.rezultati = data;
        this.prikaziRezultate = true;
      });
    }
  }

  back() {
    this.prikaziRezultate = false;
  }

  advancedSearch() {
    let params;
    params = {
      magazineName: this.magazineName,
      magazineCheck: this.magazineCheck,
      title: this.title,
      titleCheck: this.titleCheck,
      keyTerms: this.keyTerms,
      keyTermsCheck: this.keyTermsCheck,
      authors: this.authors,
      authorsCheck: this.authorsCheck,
      content: this.content,
      contentCheck: this.contentCheck
    }

    console.log(params);

    this.searchService.advancedSearch(params).subscribe(data => {
      console.log(data);
      this.rezultati = data;
      this.prikaziRezultate = true;
    });

  }

  downloadPDF(id) {
    this.workService.getPath(id).subscribe( data => {
      console.log(data);
      this.url = data.value;
      var size = this.url.split("\\");
      this.url = this.url.split("\\")[size.length-1];
      console.log(this.url);

      this.url = "http://127.0.0.1:8887/" + this.url;
      window.open(this.url, "_blank");
    });
  }

}
