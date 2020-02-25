import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const url='http://localhost:8080/api/search/';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  private searcchByTitleUrl=url+"byTitle/"
  private searcchByMagazineNameUrl=url+"byMagazineName/"
  private searcchByKeyTermsUrl=url+"byKeyTerms/"
  private searcchByAuthorsUrl=url+"byAuthors/"
  private searcchByScienceAreasUrl=url+"byScienceAreas/"
  private searchByWorkContentUrl=url+"byWorkContent/"
  private advancedSearchUrl = url + "advancedSearch"
  private getByGeoSpacingUrl=url+"getReviewersByLocation/"
  private getByMoreLikeThisUrl=url+"getMoreLikeThisReviewers/"
  private getRevsUrl=url+"getReviewersByScArea/"

  constructor(private http:HttpClient) { }

  searchByTitle(title,phrase):Observable<any>{
    return this.http.get(this.searcchByTitleUrl+phrase+"/"+title);
  }

  searchByMagazineName(name,phrase):Observable<any>{
    return this.http.get(this.searcchByMagazineNameUrl+phrase+"/"+name);
  }

  searchByKeyTerms(keyTerms,phrase):Observable<any>{
    return this.http.get(this.searcchByKeyTermsUrl+phrase+"/"+keyTerms);
  }

  searchByAuthors(authors,phrase):Observable<any>{
    return this.http.get(this.searcchByAuthorsUrl+phrase+"/"+authors);
  }

  searchByScienceAreas(sa):Observable<any>{
    return this.http.get(this.searcchByScienceAreasUrl+sa);
  }

  searchByWorkContent(workContent,phrase):Observable<any>{
    return this.http.get(this.searchByWorkContentUrl+phrase+"/"+workContent);
  }

  searchByGeoSpacing(wokrId):Observable<any>{
    return this.http.get(this.getByGeoSpacingUrl+wokrId);
  }

  searchByMoreLikeThis(workId):Observable<any>{
    return this.http.get(this.getByMoreLikeThisUrl+workId);
  }

  advancedSearch(o):Observable<any>{
    return this.http.post(this.advancedSearchUrl,o);
  }

  getRevsBySC(w) : Observable<any> {
    return this.http.get(this.getRevsUrl + w);
  }
}
