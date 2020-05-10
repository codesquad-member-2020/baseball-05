import { instance } from './index';

function fetchMatches() {
  return instance.get('matches');
}

function fetchScores() {
  return instance.get('scores');
}

export { fetchMatches, fetchScores };
