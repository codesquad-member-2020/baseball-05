import { instance } from './index';

function fetchMatches() {
  return instance.get('matches');
}

function fetchScores() {
  return instance.get('scores');
}

function fetchRoters() {
  return instance.get('rosters');
}

function fetchGames(isOffense) {
  return instance.get(`games/rounds?isOffense=${isOffense}`);
}

export { fetchMatches, fetchScores, fetchRoters, fetchGames };
