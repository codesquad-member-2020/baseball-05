import { instance } from './index';

function fetchMatches() {
  return instance.get('matches');
}

export { fetchMatches };
