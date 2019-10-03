import { Contest } from './Contest';
import { Result } from './Result';


export interface Racing {
  id: number;
  type?: string;
  distance: number;
  result?: Array<Result>;
  contest?: Contest;
}
