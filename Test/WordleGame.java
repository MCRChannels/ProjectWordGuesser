package Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class WordleGame {
    private int wordLength;
    private int maxAttempts;
    private int currentAttempt;
    private String targetWord;
    private boolean gameOver;
    private boolean won;
    
    public WordleGame(int wordLength) {
        this.wordLength = wordLength;
        this.maxAttempts = 6;
        this.currentAttempt = 0;
        this.targetWord = chooseWord();
        this.gameOver = false;
        this.won = false;
    }
    
    private String chooseWord() {
        Map<Integer, List<String>> words = new HashMap<>();
        // Easy Words (5 letters)
        words.put(5, Arrays.asList("apple", "beach", "chair", "dance", "eagle", "about", "alert", 
        "argue", "above", "alike", "arise", "began", "abuse", "alive", "array", "begin", "actor", 
        "allow", "aside", "begun", "acute", "alone", "asset", "being", "admit", "along", "audio", 
        "below", "adopt", "alter", "audit", "bench", "adult", "among", "avoid", "billy", "after", 
        "anger", "award", "birth", "again", "angle", "aware", "black", "agent", "angry", "badly", 
        "blame", "agree", "apart", "baker", "blind", "ahead", "apple", "bases", "block", "alarm", 
        "apply", "basic", "blood", "album", "arena", "basis", "board", "boost", "buyer", "china", 
        "cover", "booth", "cable", "chose", "craft", "bound", "calif", "civil", "crash", "brain", 
        "carry", "claim", "cream", "brand", "catch", "class", "crime", "bread", "cause", "clean", 
        "cross", "break", "chain", "clear", "crowd", "breed", "chair", "click", "crown", "brief", 
        "chart", "clock", "curve", "bring", "chase", "close", "cycle", "broad", "cheap", "coach", 
        "daily", "broke", "check", "coast", "dance", "brown", "chest", "could", "dated", "build", 
        "chief", "count", "dealt", "built", "child", "court", "death", "debut", "entry", "forth", 
        "group", "delay", "equal", "forty", "grown", "depth", "error", "forum", "guard", "doing", 
        "event", "found", "guess", "doubt", "every", "frame", "guest", "dozen", "exact", "frank", 
        "guide", "draft", "exist", "fraud", "happy", "drama", "extra", "fresh", "harry", "drawn", 
        "faith", "front", "heart", "dream", "false", "fruit", "heavy", "dress", "fault", "fully", 
        "hence", "drill", "fibre", "funny", "night", "drink", "field", "giant", "horse", "drive", 
        "fifth", "given", "hotel", "drove", "fifty", "glass", "house", "dying", "fight", "globe", 
        "human", "eager", "final", "going", "ideal", "early", "first", "grace", "image", "earth", 
        "fixed", "grade", "index", "eight", "flash", "grand", "inner", "elite", "fleet", "grant", 
        "input", "empty", "fluid", "great", "irony", "enjoy", "focus", "green", "juice", "enter", 
        "force", "gross", "joint", "judge", "metal", "media", "newly", "known", "local", "might", 
        "noise", "label", "logic", "minor", "north", "large", "loose", "minus", "noted", "laser", 
        "lower", "mixed", "novel", "later", "lucky", "model", "nurse", "laugh", "lunch", "money", 
        "occur", "layer", "lying", "month", "ocean", "learn", "magic", "moral", "offer", "lease", 
        "major", "motor", "often", "least", "maker", "mount", "order", "leave", "march", "mouse", 
        "other", "legal", "music", "mouth", "ought", "level", "match", "movie", "paint", "light", 
        "mayor", "needs", "paper", "peace", "power", "radio", "round", "panel", "press", "raise", 
        "route", "phase", "price", "range", "royal", "phone", "pride", "rapid", "rural", "photo", 
        "prime", "ratio", "scale", "piece", "print", "reach", "scene", "pilot", "prior", "ready", 
        "scope", "pitch", "prize", "refer", "score", "place", "proof", "right", "sense", "plain", 
        "proud", "rival", "serve", "plane", "prove", "river", "seven", "plant", "queen", "quick", 
        "shall", "plate", "sixth", "stand", "shape", "point", "quiet", "roman", "share", "pound", 
        "quite", "rough", "sharp", "sheet", "spare", "style", "times", "shelf", "speak", "sugar", 
        "tired", "shell", "speed", "suite", "title", "shift", "spend", "super", "today", "shirt", 
        "spent", "sweet", "topic", "shock", "split", "table", "total", "shoot", "spoke", "taken", 
        "touch", "short", "sport", "taste", "tough", "shown", "staff", "taxes", "tower", "sight", 
        "stage", "teach", "track", "since", "stake", "teeth", "trade", "sixty", "start", "texas", 
        "treat", "sized", "state", "thank", "trend", "skill", "steam", "their", "tried", "slide", 
        "stick", "theme", "tries", "small", "still", "there", "truck", "smart", "stock", "these", 
        "truly", "smile", "stone", "thick", "trust", "smith", "stood", "thing", "truth", "smoke", 
        "store", "think", "twice", "solid", "storm", "third", "under", "solve", "story", "those", 
        "undue", "sorry", "strip", "three", "union", "sound", "stuck", "threw", "unity", "south", 
        "study", "throw", "until", "space", "stuff", "tight", "upper", "upset", "whole", "waste", 
        "wound", "urban", "whose", "watch", "write", "usage", "woman", "water", "wrong", "usual", 
        "train", "wheel", "wrote", "valid", "world", "where", "yield", "value", "worry", "which", 
        "young", "video", "worse", "while", "youth", "virus", "worst", "white", "worth", "visit", 
        "would", "vital", "voice"));
        // Medium Words (6 letters)
        words.put(6, Arrays.asList("banana", "camera", "dragon", "eleven", "fridge", "abroad", "casual", 
        "around", "couple", "accept", "caught", "arrive", "course", "access", "centre", "artist", "covers", 
        "across", "centum", "aspect", "create", "acting", "chance", "assess", "credit", "action", "change", 
        "assist", "crisis", "active", "charge", "assume", "custom", "actual", "choice", "attack", "damage", 
        "advice", "choose", "attend", "danger", "advise", "chosen", "august", "dealer", "affect", "church", 
        "author", "debate", "afford", "circle", "avenue", "decade", "afraid", "client", "backed", "decide", 
        "agency", "closed", "barely", "defeat", "agenda", "closer", "battle", "defend", "almost", "coffee", 
        "beauty", "define", "always", "column", "became", "degree", "amount", "combat", "become", "demand", 
        "animal", "coming", "before", "depend", "annual", "common", "behalf", "deputy", "answer", "comply", 
        "behind", "desert", "anyone", "copper", "belief", "design", "anyway", "corner", "belong", "desire", 
        "appeal", "costly", "beaker", "detail", "appear", "county", "better", "detect", "beyond", "budget", 
        "during", "device", "bishop", "burden", "easily", "differ", "border", "bureau", "eating", "dinner", 
        "bottle", "button", "editor", "direct", "bottom", "camera", "effect", "doctor", "bought", "cancer", 
        "effort", "dollar", "branch", "cactus", "eighth", "domain", "breath", "carbon", "either", "double", 
        "bridge", "career", "eleven", "driven", "bright", "castle", "emerge", "driver"));
        // Hard Words (7 letters)
        words.put(7, Arrays.asList("amazing", "bicycle", "captain", "diamond", "elephant", "ability", "backing", 
        "cabinet", "absence", "balance", "calibre", "academy", "banking", "calling", "account", "barrier", 
        "capable", "accused", "battery", "capital", "achieve", "bearing", "caption", "address", "because", 
        "capture", "advance", "bedroom", "careful", "adverse", "believe", "carrier", "advised", "beneath", 
        "caution", "adviser", "benefit", "ceiling", "against", "besides", "central", "airline", "between", 
        "centric", "airport", "billion", "century", "alcohol", "binding", "certain", "alleged", "brother", 
        "chamber", "already", "brought", "channel", "analyst", "burning", "chapter", "ancient", "dealing", 
        "charity", "another", "decided", "charlie", "anxiety", "decline", "charter", "anxious", "default", 
        "checked", "anybody", "defence", "chicken", "applied", "deficit", "chronic", "arrange", "deliver", 
        "circuit", "arrival", "density", "classes", "article", "deposit", "classic", "assault", "desktop", 
        "climate", "assumed", "despite", "closing", "assured", "destroy", "closure", "attempt", "develop", 
        "clothes", "attract", "devoted", "collect", "auction", "diamond", "college", "average", "digital", 
        "combine", "eastern", "discuss", "comfort", "economy", "disease", "command", "edition", "display", 
        "comment", "elderly", "dispute", "compact", "element", "distant", "company", "engaged", "diverse", 
        "compare", "enhance", "divided", "compete", "essence", "drawing", "complex", "evening", "driving", 
        "concept", "evident", "dynamic", "concern", "exactly", "factory", "concert", "examine", "faculty", 
        "conduct", "example", "failing", "confirm", "excited", "failure", "connect", "exclude", "fashion", 
        "consent", "exhibit", "feature", "consist", "expense", "federal", "contact", "explain", "feeling", 
        "contain", "explore", "fiction", "content", "express", "fifteen", "contest", "extreme", "filling", 
        "context", "gallery", "finance", "control", "gateway", "finding", "convert", "general", "fishing", 
        "correct", "genetic", "fitness", "council", "genuine", "foreign", "counsel", "gigabit", "forever", 
        "counter", "greater", "formula", "country", "hanging", "fortune", "crucial", "heading", "forward", 
        "crystal", "healthy", "founder", "culture", "hearing", "freedom", "current", "heavily", "further", 
        "cutting", "helpful", "illegal", "jointly", "helping", "illness", "journal", "herself", "imagine", 
        "journey", "highway", "imaging", "justice", "himself", "improve", "justify", "history", "include", 
        "keeping", "holding", "initial", "killing", "holiday", "inquiry", "kingdom", "housing", "insight", 
        "kitchen", "however", "install", "knowing", "hundred", "instant", "machine", "husband", "instead", 
        "manager", "landing", "intense", "married", "largely", "interim", "massive", "lasting", "involve", 
        "maximum", "leading", "natural", "meaning", "learned", "neither", "measure", "leisure", "nervous", 
        "medical", "liberal", "network", "meeting", "liberty", "neutral", "mention", "library", "notable", 
        "message", "license", "nothing", "million", "limited", "nowhere", "mineral", "listing", "nuclear", 
        "minimal", "logical", "nursing", "minimum", "loyalty", "pacific", "missing", "obvious", "package", 
        "mission", "offence", "painted", "mistake", "officer", "parking", "mixture", "ongoing", "partial", 
        "monitor", "opening", "partner", "monthly", "operate", "passage", "morning", "opinion", "passing", 
        "musical", "optical", "passion", "mystery", "organic", "passive", "portion", "outcome", "patient", 
        "poverty", "outdoor", "pattern", "precise", "outlook", "payable", "predict", "outside", "payment", 
        "premier", "overall", "penalty", "premium", "proudly", "pending", "prepare", "project", "pension", 
        "present", "promise", "pealing", "prevent", "promote", "perfect", "primary", "protect", "perform", 
        "printer", "protein", "perhaps", "privacy", "protest", "phoenix", "private", "provide", "picking", 
        "problem", "publish", "picture", "proceed", "purpose", "pioneer", "process", "pushing", "plastic", 
        "produce", "qualify", "pointed", "product", "quality", "popular", "profile", "quarter", "section", 
        "success", "radical", "segment", "suggest", "railway", "serious", "summary", "readily", "service", 
        "support", "reading", "serving", "suppose", "reality", "session", "supreme", "realise", "setting", 
        "surface", "receipt", "seventh", "surgery", "receive", "several", "surplus", "recover", "shortly", 
        "survive", "reflect", "showing", "suspect", "regular", "silence", "sustain", "related", "silicon", 
        "teacher", "release", "similar", "telecom", "remains", "sitting", "telling", "removal", "sixteen", 
        "tension", "removed", "skilled", "theatre", "replace", "smoking", "therapy", "request", "society", 
        "thereby", "require", "somehow", "thought", "reserve", "someone", "through", "resolve", "speaker", 
        "tonight", "respect", "special", "totally", "respond", "species", "touched", "restore", "sponsor", 
        "towards", "retired", "station", "traffic", "revenue", "storage", "trouble", "reverse", "strange", 
        "turning", "rollout", "stretch", "typical", "routine", "student", "uniform", "running", "studied", 
        "unknown", "satisfy", "subject", "unusual", "science", "succeed", "upgrade", "walking", "whether", 
        "upscale", "wanting", "willing", "utility", "warning", "winning", "variety", "warrant", "without", 
        "various", "wearing", "witness", "vehicle", "weather", "working", "venture", "webcast", "writing", 
        "version", "website", "written", "veteran", "wedding", "western", "victory", "weekend", "whereas", 
        "viewing", "welcome", "whereby", "village", "welfare", "virtual", "violent", "visible", "waiting"));

        
        List<String> wordList = words.get(wordLength);
        return wordList.get(new Random().nextInt(wordList.size()));
    }
    
    public String[] guess(String word) {
        if (word.length() != wordLength || !word.matches("[a-zA-Z]+")) {
            return null;
        }
        
        currentAttempt++;
        String[] result = new String[wordLength];
        
        for (int i = 0; i < wordLength; i++) {
            if (word.charAt(i) == targetWord.charAt(i)) {
                result[i] = "green";
            } else if (targetWord.contains(String.valueOf(word.charAt(i)))) {
                result[i] = "yellow";
            } else {
                result[i] = "gray";
            }
        }
        
        if (word.equals(targetWord)) {
            won = true;
            gameOver = true;
        } else if (currentAttempt >= maxAttempts) {
            gameOver = true;
        }
        
        return result;
    }
    
    public Map<String, Object> getGameState() {
        Map<String, Object> state = new HashMap<>();
        state.put("attemptsLeft", maxAttempts - currentAttempt);
        state.put("gameOver", gameOver);
        state.put("won", won);
        state.put("targetWord", gameOver ? targetWord : null);
        return state;
    }
    
    public int getWordLength() {
        return wordLength;
    }
}