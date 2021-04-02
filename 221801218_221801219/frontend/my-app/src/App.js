import React from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import Home from './Home';
import CreateSearchPageList from './SearchPaper';
import CreateSearchDetailPage from './PaperDetail';

class App extends React.Component {

    render() {
        return (
            <Router>
                <Route exact path="/" component={Home} />
                <Route path="/:id" component={Home} />
                <Route path="/SearchPaperList/:id" component={CreateSearchPageList} />
                <Route path="/SearchPaperList/:id/:meeting/:value" component={CreateSearchPageList} />
                <Route path="/SearchPaperDetail/:id" component={CreateSearchDetailPage} />
            </Router>
        )
    }
}
export default App;