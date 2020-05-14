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
  return instance.get(`games/rounds?matchesId=${isOffense}`);
}

function fetchUser(selectTeamObj) {
  return instance.post('games', selectTeamObj);
}

export { fetchMatches, fetchScores, fetchRoters, fetchGames, fetchUser };
