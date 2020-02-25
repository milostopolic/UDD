import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WorkService {

  constructor(private httpClient: HttpClient) { }
  
  postBasicInfo(user, asd) {
    return this.httpClient.post("http://localhost:8080/api/work/postWorkBasicInfo/", user) as Observable<any>;
  }

  postNewWork(user) {
    return this.httpClient.post("http://localhost:8080/api/work/postWorkBasicInfo/", user) as Observable<any>;
  }

  getujCasopise() : Observable<any> {
    return this.httpClient.get("http://localhost:8080/api/work/getAllMagazines/");
  }

  getujNaucneOblasti() : Observable<any> {
    return this.httpClient.get("http://localhost:8080/api/work/getNaucneOblasti/");
  }

  postujRecenzente(wid, l) : Observable<any> {
    return this.httpClient.post("http://localhost:8080/api/work/postReviewers/" + wid, l);
  }

  uploadFile( fileToUpload: File) {
    const formData: FormData = new FormData();  
    formData.append("File", fileToUpload);
    return this.httpClient.post('http://localhost:8080/api/work/uploadPDF/', formData,{ responseType: 'text'});
  }

  getPath(wid) : Observable<any> {
    return this.httpClient.get('http://localhost:8080/api/work/pdfname/' + wid);
  }

  

}
