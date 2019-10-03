import { Racing } from './Racing';


export interface Contest {
  idContest: number;
  location?: string;
  date?: string;
  racings?: Array<Racing>;
}
